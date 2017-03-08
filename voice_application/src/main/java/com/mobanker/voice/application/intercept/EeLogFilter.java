package com.mobanker.voice.application.intercept;

import com.alibaba.dubbo.rpc.*;
import com.alibaba.fastjson.JSONObject;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import com.mobanker.framework.tracking.EE;
import com.mobanker.framework.tracking.support.SpringContextUtils;
import com.mobanker.voice.common.constants.ErrorConstant;
import com.mobanker.voice.common.constants.SystemEnum;
import com.mobanker.voice.common.constants.ZkConfigConstant;
import com.mobanker.voice.dubbo.dto.log.BaseLogDto;
import com.mobanker.voice.dubbo.dto.log.EsbServiceLogDto;
import com.mobanker.voice.dubbo.service.log.EsbServiceLogDubboService;
import com.mobanker.voice.business.common.EsbCommonBusiness;
import com.mobanker.voice.common.exception.VoiceException;
import com.mobanker.voice.common.logger.LogManager;
import com.mobanker.voice.common.logger.Logger;
import com.mobanker.voice.common.packet.ResponseBuilder;
import com.mobanker.voice.common.packet.ResponseEntity;
import com.mobanker.voice.common.annotation.NoEELog;
import com.mobanker.voice.common.utils.MapRunable;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.mobanker.voice.application.intercept.util.FilterConvert.initBaseLogDto;
import static com.mobanker.voice.application.intercept.util.FilterConvert.setDubboMonitorEnd;
import static com.mobanker.voice.application.intercept.util.FilterConvert.setSuccessInfo;

/**
 * @author hantongyang
 * @description
 * @time 2017/2/4 13:53
 */
public class EeLogFilter extends CustomFilter {

    private static final Logger LOG = LogManager.getSlf4jLogger(EeLogFilter.class);

    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    private static ExecutorService POOL = Executors.newFixedThreadPool(100);

    /**
     * @param invoker
     * @param invocation
     * @return com.alibaba.dubbo.rpc.Result
     * @description 鹰眼Dubbo类型访问流水记录
     * @author hantongyang
     * @time 2017/2/4 13:54
     * @method doInvoke
     */
    @Override
    public Result doInvoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        LOG.info("==============开始拦截===============");
        //1、获取类名和方法名
        String methodName = invocation.getMethodName();
        Class<?>[] parameterTypes = invocation.getParameterTypes();
        Class<?> interface1 = invoker.getInterface();
        //2、判断是否需要转化为dubboResult对象类型，根据注解
        if (invocation instanceof RpcInvocation && checkUseAnnotation(methodName, parameterTypes, interface1)) {
            LOG.info("==============跳过拦截===============");
            return invoker.invoke(invocation);
        }
        //3、声明鹰眼插件、声明日志实体
        LOG.info("==============正常拦截===============");
        //3.1
        String url = interface1.getName() + "." + methodName;
        Transaction trans = EE.newTransaction("URL", url);
        //3.2 日志实体
        EsbServiceLogDto logDto = initBaseLogDto(invoker.getInterface().getPackage().getName(), invoker.getInterface().getSimpleName(),
                invocation.getMethodName(), new Date(), EsbServiceLogDto.class);
        try {
            //4、执行方法
            Result result = invoker.invoke(invocation);
            //如果是rest的接口，进行统一的dubboResult的数据封装
            RpcResult proceed = (RpcResult) result;
            Throwable exception = proceed.getException();
            //判断返回值类型是否是ResponseEntity，如果类型错误则抛出异常
            Object resultValue = result.getValue();
            /*if(!(resultValue instanceof ResponseEntity)){
                throw new VoiceException(com.mobanker.voice.common.constants.ErrorConstant.ESB_RESPONSE_RESULT);
            }*/
            //验证返回值中是否有异常
            if (exception != null) {
                return createExceptionResult(exception, trans, url, interface1.getSimpleName(), logDto);
            }
            proceed.setValue(ResponseBuilder.normalResponse(resultValue));
            LOG.info("==============正常返回===============");
            setSuccessInfo(logDto, resultValue);
            trans.setStatus(Transaction.SUCCESS);
            return result;
        } catch (RuntimeException e) {//捕获所有的运行期异常，并进行对应的处理
            //5、如果执行有异常，也需要判断是否是rest的dubbo
            return createExceptionResult(e, trans, url, interface1.getSimpleName(), logDto);
        } finally {
            //6、执行鹰眼插件
            trans.complete();
            //7、保存日志
            doSaveLog(logDto, invocation.getArguments());
        }
    }

    /**
     * @param invoker
     * @param invocation
     * @return com.alibaba.dubbo.rpc.Result
     * @description 鹰眼Rest类型访问流水记录
     * @author hantongyang
     * @time 2017/2/4 13:55
     * @method doRestInvoke
     */
    @Override
    public Result doRestInvoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // TODO Nothing
        return invoker.invoke(invocation);
    }

    /**
     * @param methodName
     * @param parameterTypes
     * @param interface1
     * @return
     * @Description: 验证是否需要进行dubboResult封装，返回true就是不需要
     * @author hantongyang
     * @date 2016-5-19 下午5:36:57
     */
    private static boolean checkUseAnnotation(String methodName, Class<?>[] parameterTypes, Class<?> interface1) {
        //获取类和方法的反射对象，根据反射获取所有的方法参数数组
        try {
            //验证是否要被拦截
            NoEELog annotation = interface1.getAnnotation(NoEELog.class);
            if (annotation != null) {
                return true;
            }
            Method declaredMethod = interface1.getDeclaredMethod(methodName, parameterTypes);
            annotation = declaredMethod.getAnnotation(NoEELog.class);
            if (annotation != null) {
                return true;
            } else {
                return false;
            }
        } catch (NoSuchMethodException e) {
            return false;
        } catch (SecurityException e) {
            return false;
        }
    }

    /**
     * @param e
     * @param trans
     * @param url
     * @param className
     * @return com.alibaba.dubbo.rpc.RpcResult
     * @description 发生异常时的返回处理方式
     * @author hantongyang
     * @time 2017/2/4 13:59
     * @method createExceptionResult
     */
    private static RpcResult createExceptionResult(Throwable e, Transaction trans, String url, String className, BaseLogDto dto) {
        LOG.info("==============异常返回===============", e);
        //异常编码
        if (e instanceof VoiceException) {
            VoiceException be = (VoiceException) e;
            //生成鹰眼日志
            trans.setStatus(Transaction.SUCCESS);
            EE.logEvent("Monitor_SYD_EsbException", url + "-" + be.message, Event.SUCCESS, className);
            //初始化日志错误信息
            dto.setErrorCode(be.errCode);
            dto.setErrorResult(be.message);
            return new RpcResult(ResponseBuilder.errorResponse(be.errCode, be.message));
        } else {
            //初始化日志错误信息
            dto.setErrorCode(com.mobanker.voice.common.constants.ErrorConstant.SYSTEM_FAIL.getCode());
            //将堆栈信息转换成流保存到库中
            ByteArrayOutputStream buf = new ByteArrayOutputStream();
            e.printStackTrace(new PrintWriter(buf, true));
            dto.setErrorResult(buf.toString());
            if (e instanceof RuntimeException) {
                //生成鹰眼日志
                trans.setStatus(Transaction.SUCCESS);
                EE.logEvent("Monitor_SYD_ESB_EsbException", url + "-" + e.getMessage(), Event.SUCCESS, className);
                return new RpcResult(ResponseBuilder.errorResponse(com.mobanker.voice.common.constants.ErrorConstant.SERVICE_FAIL.getCode(), e.getMessage()));
            } else {
                //生成鹰眼日志
                trans.setStatus(e);
                EE.logError("闪宜贷综合服务-方法[" + url + "]发生运行时异常-", e);
                return new RpcResult(ResponseBuilder.errorResponse(com.mobanker.voice.common.constants.ErrorConstant.SYSTEM_FAIL.getCode(), com.mobanker.voice.common.constants.ErrorConstant.SYSTEM_FAIL.getDesc()));
            }
        }
    }

    /**
     * @param dto
     * @return void
     * @description 保存日志
     * @author hantongyang
     * @time 2017/2/7 11:41
     * @method doSaveLog
     */
    private void doSaveLog(BaseLogDto dto, Object[] params) {
        //判断业务调用是否正常，，如果业务调用是正常的，判断日志闸口是否需要记录正确的日志
        if (dto != null && SystemEnum.BUSINESS_SUCCESS.getValue().equals(dto.getIsSuccess())) {
            String cacheSysConfig = null;
            try {
                EsbCommonBusiness esbCommonBusiness = (EsbCommonBusiness) SpringContextUtils.getBean("esbCommonBusiness");
                cacheSysConfig = esbCommonBusiness.getCacheSysConfig(ZkConfigConstant.SYD_ESB_SERVICE_LOG_GATE.getZkValue());
            } catch (Exception e) {
                throw new VoiceException(ErrorConstant.CONFIG_DATA_NULL);
            }
            //如果返回值为0表示不需要记录正常的日志
            if (SystemEnum.BUSINESS_FAILED.getValue().equals(cacheSysConfig)) {
                return;
            }
        }
        //封装参数保存日志
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("params", JSONObject.toJSONString(params));
        map.put("dto", dto);
        POOL.execute(new MapRunable(map) {
            @Override
            public void run() {
                EsbServiceLogDubboService esbServiceLogDubboService = null;
                try {
                    esbServiceLogDubboService = (EsbServiceLogDubboService) SpringContextUtils.getBean("esbServiceLogDubboService");
                    EsbServiceLogDto dto = (EsbServiceLogDto) map.get("dto");
                    dto.setReqParam(JSONObject.toJSONString(params)); //参数
                    setDubboMonitorEnd(dto);
                    esbServiceLogDubboService.saveLog(dto);
                } catch (Exception e) {
                    LOG.info("=====Save Esb Service Log Error：" + e.getMessage());
                }
            }
        });
    }
}

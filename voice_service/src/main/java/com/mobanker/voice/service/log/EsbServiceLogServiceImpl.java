package com.mobanker.voice.service.log;

import com.mobanker.voice.dubbo.dto.log.EsbServiceLogDto;
import com.mobanker.voice.dubbo.service.log.EsbServiceLogDubboService;
import com.mobanker.voice.business.log.EsbServiceLogBusiness;
import com.mobanker.voice.common.logger.LogManager;
import com.mobanker.voice.common.logger.Logger;
import com.mobanker.voice.common.utils.BeanUtil;
import com.mobanker.voice.model.entity.log.EsbServiceLogEntity;

import javax.annotation.Resource;

/**
 * @desc:综合服务系统service模块访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:18
 */
public class EsbServiceLogServiceImpl implements EsbServiceLogDubboService {

    public static final Logger LOGGER = LogManager.getSlf4jLogger(EsbServiceLogServiceImpl.class);
    @Resource
    private EsbServiceLogBusiness esbServiceLogBusiness;


    /**
     * @param logDto
     * @return void
     * @description 综合服务系统service模块访问日志
     * @author Richard Core
     * @time 2017/2/6 17:41
     * @method saveLog
     */
    @Override
    public void saveLog(EsbServiceLogDto logDto) {
        if (logDto == null) {
            LOGGER.warn("综合服务系统service模块访问日志-记录日志没有参数");
            return;
        }
        try {
            EsbServiceLogEntity logEntity = BeanUtil.cloneBean(logDto, EsbServiceLogEntity.class);
            esbServiceLogBusiness.saveLog(logEntity);
        } catch (Exception e) {
            LOGGER.warn("综合服务系统service模块访问日志异常" + e);
        }
    }
}

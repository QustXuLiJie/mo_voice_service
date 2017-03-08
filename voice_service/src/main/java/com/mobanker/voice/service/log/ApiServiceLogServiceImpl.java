package com.mobanker.voice.service.log;

import com.mobanker.voice.dubbo.dto.log.ApiServiceLogDto;
import com.mobanker.voice.dubbo.service.log.ApiServiceLogDubboService;
import com.mobanker.voice.business.log.ApiServiceLogBusiness;
import com.mobanker.voice.common.constants.ErrorConstant;
import com.mobanker.voice.common.exception.VoiceException;
import com.mobanker.voice.common.logger.LogManager;
import com.mobanker.voice.common.logger.Logger;
import com.mobanker.voice.common.packet.ResponseBuilder;
import com.mobanker.voice.common.packet.ResponseEntity;
import com.mobanker.voice.common.utils.BeanUtil;
import com.mobanker.voice.model.entity.log.ApiServiceLogEntity;

import javax.annotation.Resource;

/**
 * @desc:API系统service模块业务日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
public class ApiServiceLogServiceImpl implements ApiServiceLogDubboService {
    public static final Logger LOG = LogManager.getSlf4jLogger(ApiServiceLogServiceImpl.class);
    @Resource
    private ApiServiceLogBusiness apiServiceLogBusiness;

    /**
     * @param logDto
     * @return com.mobanker.voice.common.packet.ResponseEntity
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 17:18
     * @method saveLog
     */
    @Override
    public ResponseEntity saveLog(ApiServiceLogDto logDto) {
        try {
            if (logDto == null) {
                throw new VoiceException(ErrorConstant.PARAM_REQUIRED);
            }
            ApiServiceLogEntity logEntity = BeanUtil.cloneBean(logDto, ApiServiceLogEntity.class);
            apiServiceLogBusiness.saveLog(logEntity);
            return ResponseBuilder.normalResponse();
        } catch (Exception e) {
            LOG.warn("api工程service模块日志记录异常：" + e);
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }
}

package com.mobanker.voice.dubbo.service.log;

import com.mobanker.voice.dubbo.dto.log.ApiServiceLogDto;
import com.mobanker.voice.common.annotation.NoEELog;
import com.mobanker.voice.common.packet.ResponseEntity;

/**
 * @desc:API系统service模块业务日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
@NoEELog
public interface ApiServiceLogDubboService {
    /**
     * @param logDto
     * @return com.mobanker.voice.common.packet.ResponseEntity
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 17:18
     * @method saveLog
     */
    public ResponseEntity saveLog(ApiServiceLogDto logDto);
}

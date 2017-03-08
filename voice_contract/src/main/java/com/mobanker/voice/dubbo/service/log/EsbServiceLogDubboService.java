package com.mobanker.voice.dubbo.service.log;

import com.mobanker.voice.dubbo.dto.log.EsbServiceLogDto;
import com.mobanker.voice.common.annotation.NoEELog;

/**
 * @desc:综合服务系统service模块访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:17
 */
@NoEELog
public interface EsbServiceLogDubboService {
    /**
     * @param logDto
     * @return void
     * @description 综合服务系统service模块访问日志
     * @author Richard Core
     * @time 2017/2/6 17:41
     * @method saveLog
     */
    public void saveLog(EsbServiceLogDto logDto);
}

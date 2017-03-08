package com.mobanker.voice.business.log;

import com.mobanker.voice.model.entity.log.ApiServiceLogEntity;

/**
 * @desc:
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
public interface ApiServiceLogBusiness {
    /**
     * @param logEntity
     * @return void
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 16:34
     * @method saveLog
     */
    public void saveLog(ApiServiceLogEntity logEntity);

}

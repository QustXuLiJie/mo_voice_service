package com.mobanker.voice.business.log;

import com.mobanker.voice.model.entity.log.EsbServiceLogEntity;

/**
 * @desc:综合服务系统service模块访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:17
 */
public interface EsbServiceLogBusiness {
    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统service模块访问日志
     * @author Richard Core
     * @time 2017/2/6 16:42
     * @method saveLog
     */
    public void saveLog(EsbServiceLogEntity logEntity);
}

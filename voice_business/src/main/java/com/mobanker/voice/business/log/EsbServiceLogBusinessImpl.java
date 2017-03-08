package com.mobanker.voice.business.log;

import com.mobanker.voice.dao.log.EsbServiceLogMapper;
import com.mobanker.voice.model.entity.log.EsbServiceLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc:综合服务系统service模块访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:18
 */
@Service
public class EsbServiceLogBusinessImpl implements EsbServiceLogBusiness {
    @Autowired
    private EsbServiceLogMapper esbServiceLogMapper;

    /**
     * @param logEntity
     * @return void
     * @description 综合服务系统service模块访问日志
     * @author Richard Core
     * @time 2017/2/6 16:42
     * @method saveLog
     */
    @Override
    public void saveLog(EsbServiceLogEntity logEntity) {
        esbServiceLogMapper.insert(logEntity);
    }
}

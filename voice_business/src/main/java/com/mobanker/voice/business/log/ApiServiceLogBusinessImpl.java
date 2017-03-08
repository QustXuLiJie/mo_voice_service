package com.mobanker.voice.business.log;

import com.mobanker.voice.dao.log.ApiServiceLogMapper;
import com.mobanker.voice.model.entity.log.ApiServiceLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @desc:API系统service模块业务日志
 * @author: Richard Core
 * @create time: 2017/2/6 16:13
 */
@Service
public class ApiServiceLogBusinessImpl implements ApiServiceLogBusiness {
    @Autowired
    private ApiServiceLogMapper apiServiceLogMapper;

    /**
     * @param logEntity
     * @return void
     * @description API系统service模块业务日志
     * @author Richard Core
     * @time 2017/2/6 16:34
     * @method saveLog
     */
    @Override
    public void saveLog(ApiServiceLogEntity logEntity) {
        apiServiceLogMapper.insert(logEntity);
    }
}

package com.mobanker.voice.common.constants;

import com.mobanker.voice.common.exception.VoiceException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author hantongyang
 * @description
 * @time 2017/1/5 20:36
 */
public enum ZkConfigConstant {
    SYD_ESB_SERVICE_LOG_GATE("service_service_log_gate", "1"), //业务成功日志是否记录闸口
    //配置中心参数
    SYD_APP_VERSION("version", "1.0"), //app版本号
    SYSTEM_TIMEOUT("system.timeout", "600"), //系统过期时间
    ;

    private String zkValue;
    private String defaultValue;

    ZkConfigConstant(String zkValue, String defaultValue) {
        this.zkValue = zkValue;
        this.defaultValue = defaultValue;
    }

    public String getZkValue() {
        return zkValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @description 根据枚举的Key获取对应的枚举
     * @author hantongyang
     * @time 2017/1/17 10:07
     * @method getByKey
     * @param key
     * @return com.mobanker.shanyidai.api.common.constant.ZkConfigConstant
     */
    public static ZkConfigConstant getByKey(String key){
        if(StringUtils.isBlank(key)){
            throw new VoiceException(ErrorConstant.ENUM_ERROR);
        }
        for(ZkConfigConstant config : ZkConfigConstant.values()){
            if(config.getZkValue().equals(key)){
                return config;
            }
        }
        throw new VoiceException(ErrorConstant.ENUM_ERROR);
    }
}

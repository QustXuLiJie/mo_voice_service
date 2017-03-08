package com.mobanker.voice.common.constants;

/**
 * @author hantongyang
 * @version 1.0
 * @description 保存到Redis中的key值
 * @date 创建时间：2016/12/24 14:21
 */
public enum SysRedisKeyEnum {
    SYD_ADD_BORROW_ORDER("SYD:BORROW:ADD:ORDER:"), //保存借款单时防止重复提交缓存
    ;
    private SysRedisKeyEnum(String value) {
        this.value = value;
    }

    /**
     * @param
     * @return java.lang.String
     * @description value 的get方法
     * @author hantongyang
     * @time 2016/12/24 14:23
     * @method getValue
     */
    public String getValue() {
        return value;
    }

    private String value;
}

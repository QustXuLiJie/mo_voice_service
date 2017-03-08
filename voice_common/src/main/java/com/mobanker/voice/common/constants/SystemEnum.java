package com.mobanker.voice.common.constants;

/**
 * @author hantongyang
 * @version 1.0
 * @description
 * @date 创建时间：2016/12/23 16:52
 */
public enum SystemEnum {
    DB_STATUS_SUCCESS("1"), //数据库标识，启用状态
    DB_STATUS_FAILED("0"), //数据库标识，禁用状态
    BUSINESS_SUCCESS("1"), //业务标识，启用状态
    BUSINESS_FAILED("0"), //业务标识，禁用状态
    PRODUCT("voice"),//产品 PRODUCT
    ;

    private SystemEnum(String value) {
        this.value = value;
    }

    /**
     * @param
     * @return java.lang.String
     * @description value 的get方法
     * @author Richard Core
     * @time 2016/12/10 15:47
     * @method getValue
     */
    public String getValue() {
        return value;
    }

    private String value;
}

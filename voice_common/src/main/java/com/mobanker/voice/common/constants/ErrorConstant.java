/**
 *
 */
package com.mobanker.voice.common.constants;


/**
 * @author Richard Core
 * @description 异常或者返回码定义 闪宜贷综合服务 todo 范围待定
 * 1、返回码8位
 * 2、前两位00 为特殊返回码 00000000 为调用成功
 * 3、前两位76 为异常或者错误码
 * 4、固定保留 0-100 系统异常
 * 5、参数处理100-199  包括参数异常 数据库异常 服务器异常 解密错误
 * 6、用户 200-299 用户判断 商户判断 短信判断
 * 7、业务 300-399 借款 还款
 * 8、闪宜贷从400-1000
 * @date 2016/12/22 15:10
 */
public enum ErrorConstant {

    CODE_PREFIX("37", "CODE_PREFIX"),//ERROR_HEAD 异常开头


    //固定保留 0-100
    SUCCESS("00000000", "调用成功"),
    SYSTEM_FAIL(CODE_PREFIX.code + "000001", "系统错误请联系管理员！"),
    SERVICE_FAIL(CODE_PREFIX.code + "000002", "服务调用出错"),
    DB_TIMEOUT(CODE_PREFIX.code + "000003", "数据库连接超时"),
    CACHE_TIMEOUT(CODE_PREFIX.code + "000004", "缓存连接超时"),
    MQ_TIMEOUT(CODE_PREFIX.code + "000005", "消息队列连接超时"),
    DB_CONNECT_SPEND(CODE_PREFIX.code + "000006", "数据库连接数用尽"),
    SERVICE_VALID(CODE_PREFIX.code + "000007", "找不到服务"),
    PARAM_VALID(CODE_PREFIX.code + "000008", "参数异常"),
    PARAM_REQUIRED(CODE_PREFIX.code + "000009", "缺少必填参数"),
    PARAM_OUT_RANGE(CODE_PREFIX.code + "000010", "参数值不在取值范围"),
    SIGN_VERIFY_FAILED(CODE_PREFIX.code + "000011", "签名验证失败"),
    REQUEST_TIMEOUT(CODE_PREFIX.code + "000012", "请求超时"),
    UNAUTH_REQUEST(CODE_PREFIX.code + "000013", "无权调用"),
    INTER_VERSION_NONSUPPORT(CODE_PREFIX.code + "000014", "接口版本不支持"),
    LOGIN_TIME_OUT(CODE_PREFIX.code + "000015", "会话超时"),
    ERROR_TOKEN_EXPIRE(CODE_PREFIX.code + "000016", "登录授权码已过期"),
    ERROR_TOKEN_INVALID(CODE_PREFIX.code + "000017", "登录授权码无法识别"),
    ERROR_RESPONSE(CODE_PREFIX.code + "000018", "获取结果异常"),
    ERROR_RESTURL_FAILED(CODE_PREFIX.code + "000019", "接口URL为空"),
    ERROR_SERVICE_RESULT(CODE_PREFIX.code + "000020", "返回值转换失败"),
    ENUM_ERROR(CODE_PREFIX.code + "000021", "枚举实例转化失败"),
    ESB_RESPONSE_RESULT(CODE_PREFIX.code + "000022", "返回值类型异常"),

    //参数处理100-299  包括参数异常 数据库异常 服务器异常 解密错误
    PARAMS_ILLEGE(CODE_PREFIX.code + "000100", "参数错误"),
    PARAMS_VERISON(CODE_PREFIX.code + "000101", "应用版本号参数缺失!"),
    PARAMS_PRODUCT(CODE_PREFIX.code + "000102", "产品线参数缺失!"),
    PARAMS_CHANNEL(CODE_PREFIX.code + "000103", "渠道参数缺失!"),
    PARAMS_REQUESTIP(CODE_PREFIX.code + "000104", "请求源IP缺失!"),
    PARAMS_CAPTCHA(CODE_PREFIX.code + "000105", "验证码参数缺失!"),
    PARAMS_PHONE(CODE_PREFIX.code + "000106", "手机号参数缺失!"),
    PHONE_INVILID(CODE_PREFIX.code + "000107", "请输入正确的手机号!"),
    PARAMS_TEL(CODE_PREFIX.code + "000108", "电话参数缺失!"),
    TEL_INVILID(CODE_PREFIX.code + "000109", "请输入正确的电话号码!"),
    WRONG_PASSWORD(CODE_PREFIX.code + "000110", "密码错误!"),
    WRONG_VERIFYCODE(CODE_PREFIX.code + "000111", "验证码错误!"),
    CONFIG_DATA_NULL(CODE_PREFIX.code + "000112", "配置系统参数获取失败!"),
    BASE_ADD_PARAM_NULL(CODE_PREFIX.code + "000113", "添加渠道信息为空"),

    //300-599
    DELETE_FUZZYWORDS_FAIL(CODE_PREFIX.code + "000300", "删除模糊词失败！"),
    UPDATE_FUZZYWORDS_FAIL(CODE_PREFIX.code + "000301", "更新模糊词失败！"),
    GET_FUZZYWORDS_FAIL(CODE_PREFIX.code + "000302", "获取模糊词失败！"),;


    private String code;//异常或者返回码
    private String desc;//描述

    private ErrorConstant(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public java.lang.String getCode() {
        return code;
    }

    public java.lang.String getDesc() {
        return desc;
    }

}

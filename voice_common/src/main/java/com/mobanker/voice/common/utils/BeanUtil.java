package com.mobanker.voice.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.mobanker.voice.common.constants.ErrorConstant;
import com.mobanker.voice.common.exception.VoiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * @author Richard Core
 * @description
 * @date 2016/12/23 13:59
 */
public class BeanUtil {
    public static final Logger logger = LoggerFactory.getLogger(BeanUtil.class);

    /**
     * @param content
     * @param clazz
     * @return T
     * @description 将content转化成指定类型的JavaBean
     * @author Richard Core
     * @time 2016/12/20 15:29
     * @method parseJson
     */
    public static <T> T parseJson(String content, Class<T> clazz) {
        try {
            logger.info("将json参数转化为JavaBean,class:[{}]，content:[{}]", clazz.getSimpleName(), content);
            T t = JSONObject.parseObject(content, clazz);
            return t;
        } catch (Exception e) {
            logger.error("将json参数转化为JavaBean异常", e);
            throw new VoiceException(ErrorConstant.PARAM_VALID.getCode(), ErrorConstant.PARAM_VALID.getDesc(), e);
        }
    }

    /**
     * @param source
     * @param clazz
     * @return T
     * @description 属性拷贝
     * @author Richard Core
     * @time 2016/12/23 14:04
     * @method cloneBean
     */
    public static <T> T cloneBean(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        try {
            logger.info("拷贝属性到指定的类型,class:[{}]，source:[{}]", clazz.getSimpleName(), source);
            T t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (Exception e) {
            logger.error("拷贝属性异常", e);
            throw new VoiceException(ErrorConstant.PARAM_VALID.getCode(), ErrorConstant.PARAM_VALID.getDesc(), e);
        }
    }

}

package com.mobanker.voice.model.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhangyunkai on 2017/3/2.
 */
/**
 * @description 查询预设文本和关键词dto
 * @author zhangyunkai
 * @date 2017/3/2 17:56
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class PreTextDto implements Serializable {

    private Long  id;            //配置id
    private String preText;      //预设文本
    private String gateKey;      //关键词通过率
    private String gateText;     //普通文本通过率
    private List<String> keyList;//关键词列表

}

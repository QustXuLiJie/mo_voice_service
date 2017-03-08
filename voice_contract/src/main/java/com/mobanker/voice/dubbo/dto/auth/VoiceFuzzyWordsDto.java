package com.mobanker.voice.dubbo.dto.auth;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @description: 模糊词查询DTO
 * @author: xulijie
 * @create time: 17:51 2017/3/1
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class VoiceFuzzyWordsDto implements Serializable{
    private Long configKeyId;//关键词ID
    private String keyWords;//关键词
    private List<String> resultList;//关键词对应的模糊词
}

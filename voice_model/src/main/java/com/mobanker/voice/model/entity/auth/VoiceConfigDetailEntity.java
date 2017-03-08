package com.mobanker.voice.model.entity.auth;

import com.mobanker.framework.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * Created by zhangyunkai on 2017/3/1.
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_voice_config_detail")
public class VoiceConfigDetailEntity extends BaseEntity {

    private String status;         //是否有效状态
    private Long presetId;         //预设配置id
    private String type;           //类型，1关键词，2预设词，3模糊词
    private String typeContent;    //类型内容
    private Long configKeyId;      //关键词ID，当type=3时,才会有值(存放的时type=1的记录的id)

}

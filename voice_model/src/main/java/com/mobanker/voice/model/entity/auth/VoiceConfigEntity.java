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
public class VoiceConfigEntity extends BaseEntity{

    private String status;          //状态 1:有效， 0:无效
    private String product;         //产品（默认 shanyidai）
    private String presetText;      //预设文本
    private String presetType;      //预设类型 ('all','ios','android', 默认all)
    private String gateCommonText;  //普通文字预设阀值
    private String gateKey;         //关键字预设阀值

}

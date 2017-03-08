package com.mobanker.voice.dao.auth;

import com.mobanker.framework.dao.BaseDao;
import com.mobanker.voice.model.dto.auth.VoiceConfigParamDto;
import com.mobanker.voice.model.entity.auth.VoiceConfigEntity;

/**
 * Created by zhangyunkai on 2017/3/1.
 */
public interface VoiceConfigMapper extends BaseDao<VoiceConfigEntity> {

    /**
     * @description 保存预设文本主表
     * @author zhangyunkai
     * @date 2017/3/1 11:48
     */
    public void saveVoiceConfig(VoiceConfigParamDto dto);


    /**
     * @description 查询预设文本以及普通文本和关键词通过率
     * @author zhangyunkai
     * @date 2017/3/2 14:38
     */
    public VoiceConfigEntity getPreText(VoiceConfigEntity entity);

    /**
     * @description 根据id删除文本配置
     * @author zhangyunkai
     * @date 2017/3/3 15:40
     */
    public void deleteById(Long id);


}

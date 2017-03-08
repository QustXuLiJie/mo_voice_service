package com.mobanker.voice.dao.auth;

import com.mobanker.framework.dao.BaseDao;

import com.mobanker.voice.model.dto.auth.AuthVoiceParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigDetailParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigParamDto;
import com.mobanker.voice.model.entity.auth.AuthVoiceEntity;

import java.util.List;

/**
 * Created by zhangyunkai on 2017/2/24.
 */
public interface AuthVoiceMapper extends BaseDao<AuthVoiceEntity> {


    /**
     * @description  分页查询语音识别列表
     * @author zhangyunkai
     * @date 2017/2/24 14:29
     */
    List<AuthVoiceEntity> findPage(AuthVoiceParamDto paramDto);

    /**
     * @description 分页查询语音列表数目
     * @author zhangyunkai
     * @date 2017/3/2 21:06
     */
    Integer  findPageCount(AuthVoiceParamDto paramDto);


    /**
     * @description 讯飞异常批量通过
     * @author zhangyunkai
     * @date 2017/2/24 19:00
     */
    public void  updateStatus(AuthVoiceParamDto dto);


    /**
     * @description 保存预设文本主表
     * @author zhangyunkai
     * @date 2017/3/1 11:48
     */
    public  void  saveVoiceConfig(VoiceConfigParamDto dto);


    /**
     * @description 保存预设文本子表(关键词列表)
     * @author zhangyunkai
     * @date 2017/3/1 11:48
     */
    public void saveVoiceConfigDetail(VoiceConfigDetailParamDto dto);
}

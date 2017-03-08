package com.mobanker.voice.business.auth;

import com.mobanker.voice.common.packet.ResponseEntity;

import com.mobanker.voice.dubbo.dto.auth.VoiceFuzzyWordsDto;
import com.mobanker.voice.model.dto.auth.AuthVoiceParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigDetailParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigParamDto;
import com.mobanker.voice.model.entity.auth.AuthVoiceEntity;
import com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity;
import com.mobanker.voice.model.entity.auth.VoiceConfigEntity;

import java.util.List;

/**
 * Created by zhangyunkai on 2017/2/24.
 */
public interface AuthVoiceBusiness {

    /**
     * @description 分页查询语音识别列表
     * @author zhangyunkai
     * @date 2017/2/24 14:32
     */
    public List<AuthVoiceEntity> findPage(AuthVoiceParamDto authVoiceParamDto);

    /**
     * @description  分页查询语音识别列表
     * @author zhangyunkai
     * @date 2017/3/2 21:04
     */
    public  Integer findPageCount(AuthVoiceParamDto authVoiceParamDto);


    /**
     * @description 批量通过语音识别(讯飞异常批量通过, 主管验证通过)
     * @author zhangyunkai
     * @date 2017/2/24 18:53
     */
    public void updateStatus(AuthVoiceParamDto authVoiceParamDto);


    /**
     * @description 保存预设文本主表
     * @author zhangyunkai
     * @date 2017/3/1 11:44
     */
    public void saveVoiceConfig(VoiceConfigParamDto configParamDto);


    /**
     * @description 保存预设文本子表(关键词列表)
     * @author zhangyunkai
     * @date 2017/3/1 11:46
     */
    public void saveVoiceConfigDetail(VoiceConfigDetailParamDto configDetailParamDto);

    /**
     * @param
     * @return java.util.List<com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity>
     * @author xulijie
     * @method getKeyWords
     * @description 获取关键词
     * @time 17:11 2017/3/1
     */
    public List<VoiceConfigDetailEntity> getKeyWords();

    /**
     * @param keyWords
     * @return java.util.List<com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity>
     * @author xulijie
     * @method getFuzzyWords
     * @description 根据关键词查询模糊词
     * @time 17:27 2017/3/1
     */
    public List<VoiceConfigDetailEntity> getFuzzyWords(VoiceConfigDetailEntity keyWords);


    /**
     * @description 查询预设文本以及相关阈值
     * @author zhangyunkai
     * @date 2017/3/2 14:35
     */
    public VoiceConfigEntity getPreText(VoiceConfigEntity entity);


    /**
     * @description 根据预设配置id查询对应关键词
     * @author zhangyunkai
     * @date 2017/3/2 18:00
     */
    public  List<VoiceConfigDetailEntity> getKeywordsByPresetId(Long id);


    /**
     * @description 根据id删除预设文本
     * @author zhangyunkai
     * @date 2017/3/3 15:06
     */
    public void deleteVoiceConfig(Long id);


    /**
     * @description 根据id 删除预设关键词列表
     * @author zhangyunkai
     * @date 2017/3/3 15:07
     */
    public void deleteConfigDetail(Long id);

    public void deleteFuzzyWords();

    public void saveFuzzyWords(List<VoiceFuzzyWordsDto> paramList);

}

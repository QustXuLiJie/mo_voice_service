package com.mobanker.voice.business.auth;


import com.mobanker.voice.dao.auth.AuthVoiceMapper;
import com.mobanker.voice.dao.auth.VoiceConfigDetailMapper;
import com.mobanker.voice.dao.auth.VoiceConfigMapper;
import com.mobanker.voice.dubbo.dto.auth.VoiceFuzzyWordsDto;
import com.mobanker.voice.model.dto.auth.AuthVoiceParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigDetailParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigParamDto;
import com.mobanker.voice.model.entity.auth.AuthVoiceEntity;
import com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity;
import com.mobanker.voice.model.entity.auth.VoiceConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangyunkai on 2017/2/24.
 */
@Service
public class AuthVoiceBusinessImpl implements AuthVoiceBusiness {

    public static final String ONE = "1";
    public static final String THREE = "3";

    @Autowired
    private AuthVoiceMapper authVoiceMapper;

    @Autowired
    private VoiceConfigMapper voiceConfigMapper;

    @Autowired
    private VoiceConfigDetailMapper voiceConfigDetailMapper;


    /**
     * @description 分页查询语音识别列表
     * @author zhangyunkai
     * @date 2017/2/24 14:33
     */
    @Override
    public List<AuthVoiceEntity> findPage(AuthVoiceParamDto paramDto) {
        return authVoiceMapper.findPage(paramDto);
    }

    @Override
    public Integer findPageCount(AuthVoiceParamDto authVoiceParamDto) {
        return authVoiceMapper.findPageCount(authVoiceParamDto);
    }

    /**
     * @description 批量通过语音识别验证(讯飞异常批量验证, 主管验证审核通过)
     * @author zhangyunkai
     * @date 2017/3/1 13:41
     */
    @Override
    public void updateStatus(AuthVoiceParamDto authVoiceParamDto) {
        authVoiceMapper.updateStatus(authVoiceParamDto);
    }


    /**
     * @description 保存预设文本主表
     * @author zhangyunkai
     * @date 2017/3/1 13:42
     */
    @Override
    public void saveVoiceConfig(VoiceConfigParamDto configParamDto) {
        voiceConfigMapper.saveVoiceConfig(configParamDto);
    }


    /**
     * @description 保存预设文本子表(关键词列表)
     * @author zhangyunkai
     * @date 2017/3/1 13:43
     */
    @Override
    public void saveVoiceConfigDetail(VoiceConfigDetailParamDto configDetailParamDto) {
        voiceConfigDetailMapper.saveVoiceConfigDetail(configDetailParamDto);
    }

    /**
     * @param
     * @return java.util.List<com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity>
     * @author xulijie
     * @method getKeyWords
     * @description 获取关键词
     * @time 17:11 2017/3/1
     */
    @Override
    public List<VoiceConfigDetailEntity> getKeyWords() {
        return voiceConfigDetailMapper.findVoiceConfigKeyWords();
    }

    /**
     * @param keyWords
     * @return java.util.List<com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity>
     * @author xulijie
     * @method getFuzzyWords
     * @description 根据关键词查询模糊词
     * @time 17:27 2017/3/1
     */
    @Override
    public List<VoiceConfigDetailEntity> getFuzzyWords(VoiceConfigDetailEntity keyWords) {
        //根据关键词查询模糊词
        return voiceConfigDetailMapper.findVoiceConfigFuzzyWords(keyWords);
    }

    /**
     * @param
     * @return void
     * @author xulijie
     * @method deleteFuzzyWords
     * @description 删除所有模糊词
     * @time 15:31 2017/3/2
     */
    @Override
    public void deleteFuzzyWords() {
        voiceConfigDetailMapper.deleteAllFuzzyWords();
    }

    @Override
    public void saveFuzzyWords(List<VoiceFuzzyWordsDto> paramList) {
        //for循环保存数据
        for (VoiceFuzzyWordsDto param : paramList) {
            if (param.getResultList() != null && param.getResultList().size() > 0) {
                voiceConfigDetailMapper.saveFuzzyWords(param);
            }
        }
    }


    /**
     * @description 查询预设文本以及相关阈值
     * @author zhangyunkai
     * @date 2017/3/2 14:36
     */
    @Override
    public VoiceConfigEntity getPreText(VoiceConfigEntity entity) {
        return voiceConfigMapper.getPreText(entity);
    }

    @Override
    public List<VoiceConfigDetailEntity> getKeywordsByPresetId(Long id) {
        VoiceConfigDetailEntity detailEntity = new VoiceConfigDetailEntity();
        detailEntity.setPresetId(id);
        detailEntity.setStatus("1");
        return voiceConfigDetailMapper.get(detailEntity);
    }

    /**
     * @description 根据id删除预设文本，
     * @author zhangyunkai
     * @date 2017/3/3 15:03
     */
    @Override
    public void deleteVoiceConfig(Long id) {
        voiceConfigMapper.deleteById(id);
    }

    /**
     * @description 根据presetId删除关键词
     * @author zhangyunkai
     * @date 2017/3/3 16:11
     */
    @Override
    public void deleteConfigDetail(Long presetId) {
        voiceConfigDetailMapper.deleteConfigDetailByPresetId(presetId);
    }

}

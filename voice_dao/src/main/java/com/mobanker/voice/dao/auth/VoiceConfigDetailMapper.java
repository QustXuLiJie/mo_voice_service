package com.mobanker.voice.dao.auth;

import com.mobanker.framework.dao.BaseDao;
import com.mobanker.voice.dubbo.dto.auth.VoiceFuzzyWordsDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigDetailParamDto;
import com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhangyunkai on 2017/3/1
 */
public interface VoiceConfigDetailMapper extends BaseDao<VoiceConfigDetailEntity> {


    /**
     * @description 保存预设文本子表(关键词列表)
     * @author zhangyunkai
     * @date 2017/3/1 11:48
     */
    public void saveVoiceConfigDetail(VoiceConfigDetailParamDto dto);

    /**
     * @param
     * @return java.util.List<com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity>
     * @author xulijie
     * @method findVoiceConfigKeyWords
     * @description 查询关键词
     * @time 16:59 2017/3/1
     */
    public List<VoiceConfigDetailEntity> findVoiceConfigKeyWords();

    /**
     * @param entity
     * @return java.util.List<com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity>
     * @author xulijie
     * @method findVoiceConfigFuzzyWords
     * @description 根据关键词查询模糊词
     * @time 17:24 2017/3/1
     */
    public List<VoiceConfigDetailEntity> findVoiceConfigFuzzyWords(@Param("entity") VoiceConfigDetailEntity entity);

    /**
     * @param
     * @return int
     * @author xulijie
     * @method deleteAllFuzzyWords
     * @description 删除所有的模糊词
     * @time 16:20 2017/3/2
     */
    public int deleteAllFuzzyWords();

    /**
     * @param entity
     * @return int
     * @author xulijie
     * @method saveFuzzyWords
     * @description 批量插入模糊词记录
     * @time 21:14 2017/3/2
     */
    public int saveFuzzyWords(@Param("entity") VoiceFuzzyWordsDto entity);



    /**
     * @description  根据预设文本id删除关键词
     * @author zhangyunkai
     * @date 2017/3/3 15:10
     */
    public  void   deleteConfigDetailByPresetId(Long presetId);
}

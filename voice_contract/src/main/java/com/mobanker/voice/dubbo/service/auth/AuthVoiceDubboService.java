package com.mobanker.voice.dubbo.service.auth;

import com.mobanker.voice.common.packet.ResponseEntity;
import com.mobanker.voice.dubbo.dto.auth.AuthVoiceParam;
import com.mobanker.voice.dubbo.dto.auth.VoiceConfigDetailParam;
import com.mobanker.voice.dubbo.dto.auth.VoiceConfigParam;
import com.mobanker.voice.dubbo.dto.auth.VoiceFuzzyWordsDto;

import java.util.List;


/**
 * Created by zhangyunkai on 2017/2/24.
 */
public interface AuthVoiceDubboService {

    /**
     * @description 分页查询语音识别列表
     * @author zhangyunkai
     * @date 2017/2/24 14:46
     */
    public Object findPage(AuthVoiceParam param);

    /**
     * @description 分页查询语音列表数目
     * @author zhangyunkai
     * @date 2017/3/2 21:00
     */
    public Object findPageCount(AuthVoiceParam param);


    /**
     * @description 讯飞异常批量通过语音识别
     * @author zhangyunkai
     * @date 2017/2/24 18:39
     */
    public Object updateStatusCauseXunfeiError(List<AuthVoiceParam> dtoList);


    /**
     * @description 设置预设文字以及关键词通过率阀值等
     * @author zhangyunkai
     * @date 2017/3/1 11:11
     */
    public Object addPresetText(VoiceConfigParam voiceConfigParam, List<VoiceConfigDetailParam> detailParamList);

    /**
     * @param
     * @return com.mobanker.voice.common.packet.ResponseEntity
     * @author xulijie
     * @method getFuzzyWords
     * @description 模糊词匹配管理查询
     * @time 15:52 2017/3/1
     */
    public Object getFuzzyWords();

    public void saveFuzzyWords(List<VoiceFuzzyWordsDto> paramList);


    /**
     * @description 查询预设文本以及关键词列表等
     * @author zhangyunkai
     * @date 2017/3/2 14:24
     */
    public Object getPreTextList(String product);
}

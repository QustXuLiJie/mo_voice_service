package com.mobanker.voice.service.auth;

import com.mobanker.voice.business.auth.AuthVoiceBusiness;
import com.mobanker.voice.common.constants.ErrorConstant;
import com.mobanker.voice.common.exception.VoiceException;
import com.mobanker.voice.common.logger.LogManager;
import com.mobanker.voice.common.logger.Logger;
import com.mobanker.voice.common.packet.ResponseBuilder;
import com.mobanker.voice.common.packet.ResponseEntity;
import com.mobanker.voice.common.utils.BeanUtil;

import com.mobanker.voice.dubbo.dto.auth.AuthVoiceParam;
import com.mobanker.voice.dubbo.dto.auth.VoiceConfigDetailParam;
import com.mobanker.voice.dubbo.dto.auth.VoiceConfigParam;
import com.mobanker.voice.dubbo.dto.auth.VoiceFuzzyWordsDto;
import com.mobanker.voice.dubbo.service.auth.AuthVoiceDubboService;
import com.mobanker.voice.model.dto.auth.AuthVoiceParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigDetailParamDto;
import com.mobanker.voice.model.dto.auth.VoiceConfigParamDto;
import com.mobanker.voice.model.dto.auth.*;
import com.mobanker.voice.model.entity.auth.AuthVoiceEntity;
import com.mobanker.voice.model.entity.auth.VoiceConfigDetailEntity;
import com.mobanker.voice.model.entity.auth.VoiceConfigEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangyunkai on 2017/2/24.
 */
@Transactional
public class AuthVoiceServiceImpl implements AuthVoiceDubboService {
    public static final Logger LOGGER = LogManager.getSlf4jLogger(AuthVoiceServiceImpl.class);

    @Resource
    private AuthVoiceBusiness authVoiceBusiness;

    /**
     * @description 分页查询语音设别列表
     * @author zhangyunkai
     * @date 2017/2/24 14:52
     */
    @Override
    public Object findPage(AuthVoiceParam param) {
        try {
            if (param == null) {
                LOGGER.error("---分页查询语音列表没有参数---");
                return ResponseBuilder.errorResponse(ErrorConstant.PARAM_REQUIRED.getCode(), "分页查询缺少参数");
            }
            AuthVoiceParamDto paramDto = BeanUtil.cloneBean(param, AuthVoiceParamDto.class);
            List<AuthVoiceEntity> entityList = authVoiceBusiness.findPage(paramDto);
            List<AuthVoiceParam> list = new ArrayList<AuthVoiceParam>();
            for (AuthVoiceEntity entity : entityList) {
                list.add(BeanUtil.cloneBean(entity, AuthVoiceParam.class));
            }
            // return ResponseBuilder.normalResponse(list);
            return list;
        } catch (Exception e) {
            LOGGER.error("分页查询语音列表系统异常: " + e);
            throw new VoiceException(ErrorConstant.SYSTEM_FAIL.getCode(), e.getMessage());
        }
    }


    /**
     * @description 查询语音列表数目
     * @author zhangyunkai
     * @date 2017/3/2 21:01
     */
    @Override
    public Object findPageCount(AuthVoiceParam param) {
        AuthVoiceParamDto paramDto = BeanUtil.cloneBean(param, AuthVoiceParamDto.class);
        Integer count = authVoiceBusiness.findPageCount(paramDto);
        return count;
    }

    /**
     * @description 讯飞异常批量通过
     * @author zhangyunkai
     * @date 2017/2/24 18:50
     */
    @Override
    public Object updateStatusCauseXunfeiError(List<AuthVoiceParam> paramList) {
        // try {
        if (paramList != null && !paramList.isEmpty()) {
            AuthVoiceParamDto paramDto = null;
            for (AuthVoiceParam param : paramList) {
                paramDto = BeanUtil.cloneBean(param, AuthVoiceParamDto.class);
                authVoiceBusiness.updateStatus(paramDto);
            }
        }
        return null;
       /* } catch (Exception e) {
            LOGGER.error("语音识别人工通过失败");
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), "语音识别人工通过失败");
        }*/
    }


    /**
     * @description
     * @author zhangyunkai
     * @date 2017/3/1 16:44
     */
    @Override
    public Object addPresetText(VoiceConfigParam voiceConfigParam, List<VoiceConfigDetailParam> detailParamList) {
        // try {
        if (voiceConfigParam != null) {
            //id不为空，证明是修改，需要把原来的记录删除
            Long id = voiceConfigParam.getId();
            if (id != null) {
                authVoiceBusiness.deleteVoiceConfig(id);
                authVoiceBusiness.deleteConfigDetail(id);
            }
            VoiceConfigParamDto voiceConfigParamDto = BeanUtil.cloneBean(voiceConfigParam, VoiceConfigParamDto.class);
            voiceConfigParamDto.setPresetType("all");
            voiceConfigParamDto.setProduct("shanyidai");  //产品默认闪宜贷
            authVoiceBusiness.saveVoiceConfig(voiceConfigParamDto);
            VoiceConfigDetailParamDto detailParamDto = null;
            for (VoiceConfigDetailParam detailParam : detailParamList) {
                detailParamDto = BeanUtil.cloneBean(detailParam, VoiceConfigDetailParamDto.class);
                detailParamDto.setType("1");  //1 代表关键词
                detailParamDto.setPresetId(voiceConfigParamDto.getId());
                authVoiceBusiness.saveVoiceConfigDetail(detailParamDto);
            }
        }
        return null;
        /*} catch (Exception e) {
            LOGGER.error("预设文本配置错误");
            return ResponseBuilder.errorResponse(ErrorConstant.SYSTEM_FAIL.getCode(), "预设文本系统错误");
        }*/
    }

    /**
     * @param
     * @return com.mobanker.voice.common.packet.ResponseEntity
     * @author xulijie
     * @method getFuzzyWords
     * @description 模糊词匹配管理查询
     * @time 15:52 2017/3/1
     */
    @Override
    public Object getFuzzyWords() {
        List<VoiceFuzzyWordsDto> resultList = new ArrayList<VoiceFuzzyWordsDto>();
        try {
            //1.查询关键词
            List<VoiceConfigDetailEntity> keyWordsList = authVoiceBusiness.getKeyWords();
            //2.根据关键词查询模糊词
            for (int i = 0; i < keyWordsList.size(); i++) {
                //查询模糊词
                List<VoiceConfigDetailEntity> fuzzyWordsList = authVoiceBusiness.getFuzzyWords(keyWordsList.get(i));
                //封装返回参数
//                if (fuzzyWordsList == null || fuzzyWordsList.isEmpty()) {
//                    continue;
//                }
                List<String> tmp = new ArrayList<String>();
                for (int j = 0; j < fuzzyWordsList.size(); j++) {
                    tmp.add(fuzzyWordsList.get(j).getTypeContent());
                }
                VoiceFuzzyWordsDto voiceFuzzyWordsDto = new VoiceFuzzyWordsDto();
                voiceFuzzyWordsDto.setKeyWords(keyWordsList.get(i).getTypeContent());
                voiceFuzzyWordsDto.setConfigKeyId(keyWordsList.get(i).getId());
                voiceFuzzyWordsDto.setResultList(tmp);
                resultList.add(voiceFuzzyWordsDto);
            }
//            return ResponseBuilder.normalResponse(resultList);
            return resultList;
        } catch (Exception e) {
            LOGGER.error("获取模糊词失败");
            throw new VoiceException(ErrorConstant.GET_FUZZYWORDS_FAIL);
        }
    }

    /**
     * @param paramList
     * @return com.mobanker.voice.common.packet.ResponseEntity
     * @author xulijie
     * @method updateFuzzyWords
     * @description 更新模糊词（采用全删全插）
     * @time 14:01 2017/3/2
     */
    @Override
    public void saveFuzzyWords(List<VoiceFuzzyWordsDto> paramList) {
//        try {
        //1.删除所有模糊词
        authVoiceBusiness.deleteFuzzyWords();
        //2.重新插入所有的模糊词

        authVoiceBusiness.saveFuzzyWords(paramList);
//            return ResponseBuilder.normalResponse();
//        } catch (Exception e) {
//            LOGGER.error("保存模糊词失败");
////            return ResponseBuilder.errorResponse(ErrorConstant.UPDATE_FUZZYWORDS_FAIL, e, this.getClass().getSimpleName(), "updateFuzzyWords");
//            throw new VoiceException(ErrorConstant.UPDATE_FUZZYWORDS_FAIL);
//        }
    }


    /**
     * @description 查询预设文本以及相关阈值
     * @author zhangyunkai
     * @date 2017/3/2 16:05
     */
    @Override
    public Object getPreTextList(String product) {
        PreTextDto preTextDto = new PreTextDto();
        VoiceConfigEntity entity = new VoiceConfigEntity();
          try {
        entity.setProduct(product);
        VoiceConfigEntity configEntity = authVoiceBusiness.getPreText(entity);
        if (configEntity != null) {
            preTextDto.setGateKey(configEntity.getGateKey());
            preTextDto.setGateText(configEntity.getGateCommonText());
            preTextDto.setId(configEntity.getId());
            preTextDto.setPreText(configEntity.getPresetText());
            List<VoiceConfigDetailEntity> list = authVoiceBusiness.getKeywordsByPresetId(configEntity.getId());
            List<String> resultList = new ArrayList<String>();
            for (VoiceConfigDetailEntity detailEntity : list) {
                resultList.add(detailEntity.getTypeContent());
            }
            preTextDto.setKeyList(resultList);
        }
        return preTextDto;
        } catch (Exception e) {
            LOGGER.error("查询预设文本和关键词列表异常");
            return new VoiceException(ErrorConstant.SYSTEM_FAIL.getCode(), "查询预设文本和关键词异常");
        }


    }
}

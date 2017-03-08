package com.mobanker.voice.dubbo.dto.auth;

import com.mobanker.framework.pojo.Page;
import com.mobanker.voice.common.utils.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Table;
import java.util.Date;

/**
 * @desc:  语音比对dto
 * @author: zhangyunkai
 * @create 2017年2月24日13:48:28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@Table(name = "syd_auth_voice_record")
public class AuthVoiceParam extends Entity {

    private Long id;                //主键id
    private Long userId;            //用户id
    private String orderNo;         //科大讯飞订单号
    private String audioId;         //认证系统音频ID
    private String voiceLast;       //语音时长
    private String filePath;        //文件路径
    private String fileName;        //文件名称
    private String voiceText;       //语音解析结果文本
    private String mqResultStatus;  //语音解析状态
    private String textPassRate;    //预设文本通过率
    private String keyWordPassRate; //关键字通过率
    private String status;          //认证记录状态  VoiceOrderStatusEnum
    private String auditRemark;     //审核备注
    private String supervisorRemark;//主管备注
    private String detailReason;    //详细原因
    private Long  defaultTextId;    //预设文字id
    private String askUserPhone;    //用户手机号
    private String createTimeBegin; //创建时间段开始
    private String createTimeEnd;   //创建时间段结束
    private Date createTime;        //创建时间
    private Date updateTime;        //更新时间
    private int pageNo;              //当前页码
    private int pageSize;          //每页条数
    private String askUserName;    //用户姓名


}

package com.mobanker.voice.dubbo.dto.log;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @desc:综合服务系统访问第三方HTTP协议访问日志
 * @author: Richard Core
 * @create time: 2017/2/6 15:15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class EsbHttpLogDto extends BaseLogDto {

}

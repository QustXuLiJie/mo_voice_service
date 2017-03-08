/**
 * 
 */
package com.mobanker.voice.common.exception;

import com.mobanker.voice.common.constants.ErrorConstant;


/**
 * 业务异常处理类
 * 
 * @author chenjianping
 * @data 2016年12月15日
 */
public class VoiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8768211064581546909L;

	/**
	 * 异常编码
	 */
	public final String errCode;

	/**
	 * 异常消息
	 */
	public final String message;

	public VoiceException(Throwable e) {
		super(e);
		errCode = ErrorConstant.SYSTEM_FAIL.getCode();
		message = e.getMessage();
	}

	public VoiceException(String errCode, String message) {
		super(message);
		this.errCode = errCode;
		this.message = message;
	}

	public VoiceException(String errCode, String message, Throwable e) {
		super(message, e);
		this.errCode = errCode;
		this.message = message;
	}

	public VoiceException(ErrorConstant returnCode) {
		this(returnCode.getCode(), returnCode.getDesc());
	}

	public VoiceException(ErrorConstant returnCode, Throwable e) {
		this(returnCode.getCode(), returnCode.getDesc(), e);
	}

}

package com.wondersgroup.demo.util.exception;

import com.wondersgroup.util.CommonStatusResult;

/**
 * 自定义异常
 * 
 * @author chen
 *
 */
public final class ResultException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String errorCode;

	public ResultException(final String code, final String msg) {
		super(msg);
		this.errorCode = code;
	}

	public static ResultException returnException(String errorCode) {
		String code = errorCode;
		String messageValue = ErrorMessagePropertiesUtil.getValue(errorCode);
		if (messageValue == null) {
			code = CommonStatusResult.CODE_UNKNOWN_ERROR;
			messageValue = errorCode;
		}
		return new ResultException(code, messageValue);
	}

	public static ResultException returnExceptionWithPars(String errorCode, Object... parValues) {
		String code = errorCode;
		String messageValue = ErrorMessagePropertiesUtil.getValueWithPars(errorCode, parValues);
		if (messageValue == null) {
			code = CommonStatusResult.CODE_UNKNOWN_ERROR;
			messageValue = errorCode;
		}
		return new ResultException(code, messageValue);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}

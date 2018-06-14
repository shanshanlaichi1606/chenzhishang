package com.wondersgroup.demo.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wondersgroup.util.CommonStatusResult;

/**
 * 异常拦截
 * 
 * @author chen
 *
 */
@ControllerAdvice
public class WholeException {
	private static final Logger logger = LoggerFactory.getLogger(WholeException.class);

	/**
	 * 全局异常拦截
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public CommonStatusResult errorHandler(Exception ex) {
		// LOGGER.debug(ex.getMessage(),ex);
		// LOGGER.info(ex.getMessage(),ex);
		// LOGGER.warn(ex.getMessage(),ex);
		logger.error(ex.getMessage(), ex);
		return CommonStatusResult.fail(CommonStatusResult.CODE_UNKNOWN_ERROR, null);
	}

	/**
	 * 自定义异常拦截
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseBody
	@ExceptionHandler(value = ResultException.class)
	public CommonStatusResult myErrorHandler(ResultException ex) {
		logger.error(ex.getErrorCode(), ex);
		return new CommonStatusResult(CommonStatusResult.STATUS_FAIL, ex.getErrorCode(), ex.getMessage(), ex.toString(), null);
	}
}

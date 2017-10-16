package org.shen.daily.exception;

/**
 * @author ZhiqiangShen
 * 参数全无异常
 */
public class NoOneParamException extends StudentException{
	private static final long serialVersionUID = -3192951159146281117L;
	
	public NoOneParamException(String message) {
		super(message);
	}
}

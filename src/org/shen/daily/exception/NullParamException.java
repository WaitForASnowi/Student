package org.shen.daily.exception;

/**
 * @author ZhiqiangShen
 * 参数为空异常
 */
public class NullParamException extends StudentException {

	private static final long serialVersionUID = 6246452712825426413L;
	
	public NullParamException(String message) {
		super(message);
	}
}

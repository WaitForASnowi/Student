package org.shen.daily.exception;

/**
 * @author ZhiqiangShen
 * 学生信息管理系统的所有异常的父类
 */
public class StudentException extends RuntimeException{

	private static final long serialVersionUID = -721331882652163647L;
	
	public StudentException(String message) {
		super(message);
	}
}

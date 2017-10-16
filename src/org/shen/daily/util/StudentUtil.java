package org.shen.daily.util;

import java.util.regex.Pattern;

import org.shen.daily.constant.Constant;
import org.shen.daily.exception.NullParamException;
import org.shen.daily.exception.StudentException;
import org.shen.daily.exception.TypeNotMatchException;
import org.shen.daily.model.Student;

public class StudentUtil {
	
	/**
	 * 验证学号
	 * @param idParam
	 * @return
	 * @throws StudentException
	 */
	public static Long validId(String idParam) throws TypeNotMatchException,NullParamException{
		Long id;
		
		if(idParam!=null&&!idParam.equals("")) {
			try {
				if(!Pattern.matches(Constant.ID_REGEX, idParam)) {
					throw new TypeNotMatchException("学号格式有误");
				}
				
				id=Long.valueOf(idParam);
				return id;
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("学号格式有误");
			}
		}else {
			throw new NullParamException("学号不能为空");
		}
	}
	
	/**
	 * 用来验证参数是否为空
	 * @param idParam
	 * @param nameParam
	 * @param sexParam
	 * @param telParam
	 * @param emailParam
	 * @throws NullParamException
	 */
	public static void valid(String idParam,
			String nameParam,String sexParam,
			String telParam,String emailParam) throws NullParamException{
		
		if(idParam==null||idParam.equals("")) {
			throw new NullParamException("学号不能为空");
		}	
		if(nameParam==null||nameParam.equals("")) {
			throw new NullParamException("姓名不能为空");
		}
		if(sexParam==null||sexParam.equals("")) {
			throw new NullParamException("性别不能为空");
		}
		if(telParam==null||telParam.equals("")) {
			throw new NullParamException("电话不能为空");
		}
		if(emailParam==null||emailParam.equals("")) {
			throw new NullParamException("邮箱地址不能为空");
		}
	}
	
	/**
	 * 绑定参数到pojo中
	 * 如果不满足正则表达式则抛出异常
	 * 如果格式转换失败则抛出异常
	 * @param idParam
	 * @param nameParam
	 * @param sexParam
	 * @param telParam
	 * @param emailParam
	 * @return
	 * @throws TypeNotMatchException
	 */
	public static Student bindParam(String idParam,
			String nameParam,String sexParam,
			String telParam,String emailParam) throws TypeNotMatchException{
		
		Student student=new Student();
		
		if(idParam!=null&&!idParam.equals("")) {
			try {
				if(!Pattern.matches(Constant.ID_REGEX, idParam)) {
					throw new TypeNotMatchException("学号格式有误");
				}
				
				long id=Long.valueOf(idParam);
				student.setId(id);
				
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("学号格式有误");
			}
		}
		
		if(nameParam!=null&&!nameParam.equals("")) {
			student.setName(nameParam);
		}
		
		if(sexParam!=null&&!sexParam.equals("")) {
			try {
				Byte sex=Byte.valueOf(sexParam);
				
				if(sex==1||sex==0) {
					student.setSex(sex);
				}
				//如果不为0或1则默认为空
				
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("性别格式有误");
			}
		}
		
		if(telParam!=null&&!telParam.equals("")) {
			try {
				
				if(!Pattern.matches(Constant.TEL_REGEX, telParam)) {
					throw new TypeNotMatchException("手机号格式有误");
				}
				
				Long tel=Long.valueOf(telParam);
				student.setTel(tel);
				
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("手机号格式有误");
			}
		}
		
		if(emailParam!=null&&!emailParam.equals("")) {
			
			if(!Pattern.matches(Constant.EMAIL_REGEX, emailParam)) {
				throw new TypeNotMatchException("邮箱格式有误");
			}
			
			student.setEmail(emailParam);
		}
		
		return student;
		
	}
}

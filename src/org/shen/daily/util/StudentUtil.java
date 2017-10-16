package org.shen.daily.util;

import java.util.regex.Pattern;

import org.shen.daily.constant.Constant;
import org.shen.daily.exception.NullParamException;
import org.shen.daily.exception.StudentException;
import org.shen.daily.exception.TypeNotMatchException;
import org.shen.daily.model.Student;

public class StudentUtil {
	
	/**
	 * ��֤ѧ��
	 * @param idParam
	 * @return
	 * @throws StudentException
	 */
	public static Long validId(String idParam) throws TypeNotMatchException,NullParamException{
		Long id;
		
		if(idParam!=null&&!idParam.equals("")) {
			try {
				if(!Pattern.matches(Constant.ID_REGEX, idParam)) {
					throw new TypeNotMatchException("ѧ�Ÿ�ʽ����");
				}
				
				id=Long.valueOf(idParam);
				return id;
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("ѧ�Ÿ�ʽ����");
			}
		}else {
			throw new NullParamException("ѧ�Ų���Ϊ��");
		}
	}
	
	/**
	 * ������֤�����Ƿ�Ϊ��
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
			throw new NullParamException("ѧ�Ų���Ϊ��");
		}	
		if(nameParam==null||nameParam.equals("")) {
			throw new NullParamException("��������Ϊ��");
		}
		if(sexParam==null||sexParam.equals("")) {
			throw new NullParamException("�Ա���Ϊ��");
		}
		if(telParam==null||telParam.equals("")) {
			throw new NullParamException("�绰����Ϊ��");
		}
		if(emailParam==null||emailParam.equals("")) {
			throw new NullParamException("�����ַ����Ϊ��");
		}
	}
	
	/**
	 * �󶨲�����pojo��
	 * ���������������ʽ���׳��쳣
	 * �����ʽת��ʧ�����׳��쳣
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
					throw new TypeNotMatchException("ѧ�Ÿ�ʽ����");
				}
				
				long id=Long.valueOf(idParam);
				student.setId(id);
				
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("ѧ�Ÿ�ʽ����");
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
				//�����Ϊ0��1��Ĭ��Ϊ��
				
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("�Ա��ʽ����");
			}
		}
		
		if(telParam!=null&&!telParam.equals("")) {
			try {
				
				if(!Pattern.matches(Constant.TEL_REGEX, telParam)) {
					throw new TypeNotMatchException("�ֻ��Ÿ�ʽ����");
				}
				
				Long tel=Long.valueOf(telParam);
				student.setTel(tel);
				
			} catch (NumberFormatException e) {
				throw new TypeNotMatchException("�ֻ��Ÿ�ʽ����");
			}
		}
		
		if(emailParam!=null&&!emailParam.equals("")) {
			
			if(!Pattern.matches(Constant.EMAIL_REGEX, emailParam)) {
				throw new TypeNotMatchException("�����ʽ����");
			}
			
			student.setEmail(emailParam);
		}
		
		return student;
		
	}
}

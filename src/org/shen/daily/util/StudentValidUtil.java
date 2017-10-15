package org.shen.daily.util;

import org.shen.daily.exception.StudentException;
import org.shen.daily.model.Student;

public class StudentValidUtil {
	public static void valid(Student student) throws StudentException{
		if(student.getId()==null) {
			throw new StudentException("ѧ�Ų���Ϊ��");
		}
		if(student.getName()==null||student.getName().equals("")) {
			throw new StudentException("��������Ϊ��");
		}
		if(student.getSex()==null) {
			throw new StudentException("�Ա���Ϊ��");
		}
		if(student.getTel()==null) {
			throw new StudentException("�绰����Ϊ��");
		}
		if(student.getEmail()==null||student.getEmail().equals("")) {
			throw new StudentException("�����ַ����Ϊ��");
		}
	}
}

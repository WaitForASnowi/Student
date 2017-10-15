package org.shen.daily.util;

import org.shen.daily.exception.StudentException;
import org.shen.daily.model.Student;

public class StudentValidUtil {
	public static void valid(Student student) throws StudentException{
		if(student.getId()==null) {
			throw new StudentException("学号不能为空");
		}
		if(student.getName()==null||student.getName().equals("")) {
			throw new StudentException("姓名不能为空");
		}
		if(student.getSex()==null) {
			throw new StudentException("性别不能为空");
		}
		if(student.getTel()==null) {
			throw new StudentException("电话不能为空");
		}
		if(student.getEmail()==null||student.getEmail().equals("")) {
			throw new StudentException("邮箱地址不能为空");
		}
	}
}

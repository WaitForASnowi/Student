package org.shen.daily.test;

import java.sql.SQLException;

import org.junit.Test;
import org.shen.daily.exception.NoOneParamException;
import org.shen.daily.exception.NullParamException;
import org.shen.daily.exception.StudentException;
import org.shen.daily.exception.TypeNotMatchException;
import org.shen.daily.model.Page;
import org.shen.daily.model.Student;
import org.shen.daily.service.StudentService;

/**
 * @author ZhiqiangShen
 * ≤‚ ‘StudentService
 */
public class TestStudentService {
	StudentService studentService=new StudentService();
	
	@Test
	public void testList() {
		try {
			Page<Student> page=studentService.list("1", "5");
			System.out.println(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearch() {
		try {
			Page<Student> page=studentService.search("1", null, "1", null, null, "1", "5");
			System.out.println(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet() {
		try {
			Student student=studentService.get(null);
			System.out.println(student);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdd() {
			String idParam=null;
			String nameParam="1";
			String sexParam="1";
			String telParam=null;
			String emailParam="1";
			try {
				studentService.add(idParam, nameParam, sexParam, telParam, emailParam);
			} catch (StudentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@Test
	public void testDelete() {
		String idParam="asd";
		try {
			studentService.delete(idParam);
		} catch (StudentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		String idParam="2";
		String nameParam="1";
		String sexParam="1";
		String telParam=null;
		String emailParam="1";
		try {
			studentService.updeate(idParam, nameParam, sexParam, telParam, emailParam);
		} catch (NullParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TypeNotMatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoOneParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

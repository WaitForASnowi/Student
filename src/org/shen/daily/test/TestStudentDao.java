package org.shen.daily.test;

import java.sql.SQLException;

import org.junit.Test;
import org.shen.daily.dao.StudentDao;
import org.shen.daily.model.Page;
import org.shen.daily.model.Student;

/**
 * ≤‚ ‘studentDao
 * @author ZhiqiangShen
 *
 */
public class TestStudentDao {
	private StudentDao studentDao=new StudentDao();
	
	@Test
	public void tsetList() {
		try {
			Page<Student> page=studentDao.list(21, 5);
			System.out.println(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearch() {
		Student searchModel=new Student();
		searchModel.setName("–’√˚111");
		try {
			Page<Student> page=studentDao.search(searchModel, 1, 5);
			System.out.println(page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGet() {
		Student student;
		try {
			student = studentDao.get(111L);
			System.out.println(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAdd() {
		Student student=new Student(2016220305024L, "…Í÷æ«ø", (byte)1, 18581517585L, "1422537078@qq.cpm");
		try {
			studentDao.add(student);
		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() {
		Student student=new Student();
		student.setId(2016220305024L);
		student.setName("XXX");
		try {
			studentDao.update(student);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

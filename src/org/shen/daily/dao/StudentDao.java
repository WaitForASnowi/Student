package org.shen.daily.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.shen.daily.exception.StudentException;
import org.shen.daily.model.Page;
import org.shen.daily.model.Student;
import org.shen.daily.util.DBUtil;


/**
 * @author ZhiqiangShen
 * 学生数据库操作层
 */
public class StudentDao {
	
	private Connection connection;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public StudentDao() {
		connection=DBUtil.getConnection();
	}
	
	/**
	 * 分页查询学生列表
	 * @param searchStudent
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws SQLException 
	 */
	public Page<Student> list(int currentPage,int pageSize) throws SQLException{
		
		List<Student> students=new ArrayList<>();
		int totalNum=0;
		
		int begin=(currentPage-1)*pageSize;
		
		String sql="SELECT id,name,sex,tel,email FROM student_ ORDER BY id LIMIT ?,?";
		String countSql="SELECT COUNT(id) FROM student_";
		
		//查询指定页对应的学生列表
		try {
			ps=connection.prepareStatement(sql);
			ps.setInt(1, begin);
			ps.setInt(2, pageSize);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Student student=new Student();
				student.setId(rs.getLong(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getByte(3));
				student.setTel(rs.getLong(4));
				student.setEmail(rs.getString(5));
				students.add(student);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
		
		//查询总的记录数
		try {
			ps=connection.prepareStatement(countSql);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				totalNum=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			throw e;
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
		
		Page<Student> page=new Page<>(currentPage, pageSize, totalNum, students);
		
		return page;
	}
	
	/**
	 * 搜索分页学生列表
	 * @param searchModel
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws SQLException 
	 */
	public Page<Student> search(Student searchModel,int currentPage,int pageSize) throws SQLException{
		
		List<Student> students=new ArrayList<>();
		int totalNum=0;
		
		int begin=(currentPage-1)*pageSize;
		
		StringBuffer sql=new StringBuffer("SELECT id,name,sex,tel,email FROM student_ WHERE 1=1 ");
		StringBuffer countSql=new StringBuffer("SELECT COUNT(id) FROM student_ WHERE 1=1 ");
		
		Long id=searchModel.getId();
		String name= searchModel.getName();
		Byte sex=searchModel.getSex();
		Long tel=searchModel.getTel();
		String email=searchModel.getEmail();
		
		//用于顺序存放sql中参数
		List<Object> paramList=new ArrayList<>();
		
		if(id!=null) {
			sql.append("AND CAST(id AS CHAR) LIKE ? ");
			countSql.append("AND CAST(id AS CHAR) LIKE ? ");
			paramList.add(id);
		}
		if(name!=null&&!name.equals("")) {
			sql.append("AND name LIKE ? ");
			countSql.append("AND name LIKE ? ");
			paramList.add(name);
		}
		if(sex!=null) {
			sql.append("AND CAST(sex AS CHAR) LIKE ? ");
			countSql.append("AND CAST(sex AS CHAR) LIKE ? ");
			paramList.add(sex);
		}
		if(tel!=null) {
			sql.append("AND CAST(tel AS CHAR) LIKE ? ");
			countSql.append("AND CAST(tel AS CHAR) LIKE ? ");
			paramList.add(tel);
		}
		if(email!=null&&!email.equals("")) {
			sql.append("AND email LIKE ? ");
			countSql.append("AND email LIKE ? ");
			paramList.add(email);
		}
		
		sql.append(" ORDER BY id LIMIT ?,?");
		
		//搜索指定页对应学生
		ps=connection.prepareStatement(sql.toString());
		try {
			
			int i;
			for (i = 0; i < paramList.size(); i++) {
				ps.setObject(i+1, "%"+paramList.get(i)+"%");
			}
			ps.setInt(++i, begin);
			ps.setInt(++i, pageSize);
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				Student student=new Student();
				student.setId(rs.getLong(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getByte(3));
				student.setTel(rs.getLong(4));
				student.setEmail(rs.getString(5));
				students.add(student);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
		
		//查询满足条件的记录总数
		ps=connection.prepareStatement(countSql.toString());
		try {
			int i;
			for (i = 0; i < paramList.size(); i++) {
				ps.setObject(i+1, paramList.get(i));
			}
			
			rs=ps.executeQuery();
			
			while(rs.next()) {
				totalNum=rs.getInt(1);
			}
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
		
		Page<Student> page=new Page<>(currentPage, pageSize, totalNum, students);
		
		return page;
	}
	
	/**
	 * 查询指定学生的详细信息
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Student get(Long id) throws SQLException {
		
		Student student = null;
		
		String sql="SELECT id,name,sex,tel,email FROM student_ WHERE id=?";
		
		try {
			ps=connection.prepareStatement(sql);
			ps.setLong(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				student=new Student();
				student.setId(rs.getLong(1));
				student.setName(rs.getString(2));
				student.setSex(rs.getByte(3));
				student.setTel(rs.getLong(4));
				student.setEmail(rs.getString(5));
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
		
		return student;
	}
	
	/**
	 * 增加一条学生信息
	 * @param student
	 * @throws SQLException
	 */
	public void add(Student student) throws SQLException{
		String sql="INSERT student_ (id,name,sex,tel,email) VALUES (?,?,?,?,?)";
		try {
			ps=connection.prepareStatement(sql);
			
			ps.setLong(1, student.getId());
			ps.setString(2, student.getName());
			ps.setByte(3, student.getSex());
			ps.setLong(4, student.getTel());
			ps.setString(5, student.getEmail());
			
			ps.execute();

		} catch (SQLException e) {
			throw e;
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
	}
	
	/**
	 * 删除指定学生信息
	 * @param id
	 * @throws StudentException
	 * @throws SQLException
	 */
	public void delete(long id) throws SQLException {
		
		String sql="DELETE FROM student_ WHERE id=?";
		
		try {
			ps=connection.prepareStatement(sql);
			
			ps.setLong(1, id);
			
			ps.execute();
		} catch (SQLException e) {
			throw e;
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
	}
	
	public void update(Student student) throws SQLException{
		
		StringBuilder preSql=new StringBuilder("UPDATE student_ SET ");
		
		//顺序存放sql参数
		List<Object> paramList=new ArrayList<>();
		
		if(student.getName()!=null) {
			preSql.append("name=?,");
			paramList.add(student.getName());
		}
		if(student.getSex()!=null) {
			preSql.append("sex=?,");
			paramList.add(student.getSex());
		}
		if(student.getTel()!=null) {
			preSql.append("tel=?,");
			paramList.add(student.getTel());
		}
		if(student.getEmail()!=null) {
			preSql.append("email=?,");
			paramList.add(student.getEmail());
		}
		
		String sql=preSql.toString();
		
		if(sql.endsWith(",")) {
			sql=sql.substring(0, sql.length()-1);
		}
		
		sql=sql+" WHERE id=?";

		try {
			ps=connection.prepareStatement(sql);
			
			int i;
			
			//将参数顺序放入preparedStatement
			for (i = 0; i < paramList.size(); i++) {
				ps.setObject(i+1, paramList.get(i));
			}
			
			ps.setLong(++i, student.getId());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			throw e;
		} finally {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
		}
		
	}
	
}

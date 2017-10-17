package org.shen.daily.service;

import java.sql.SQLException;

import org.shen.daily.constant.Constant;
import org.shen.daily.dao.StudentDao;
import org.shen.daily.exception.NoOneParamException;
import org.shen.daily.exception.NullParamException;
import org.shen.daily.exception.StudentRepeatException;
import org.shen.daily.exception.TypeNotMatchException;
import org.shen.daily.model.Page;
import org.shen.daily.model.Student;
import org.shen.daily.util.StudentUtil;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

/**
 * @author ZhiqiangShen
 * ��ѧ����Ϣ�йص�ҵ���߼�
 */
public class StudentService {
	private StudentDao studentDao;
	
	public StudentService() {
		this.studentDao=new StudentDao();
	}
	
	public Page<Student> list(String currentPageParam,String pageSizeParam) throws SQLException{
		try {
			//��֤�������
			int currentPage;
			int pageSize;
			if(currentPageParam==null) {
				currentPage=Constant.DEFAULT_CURRENT_PAGE;
			}else {
				try {
					currentPage=Integer.valueOf(currentPageParam);
					if(currentPage<Constant.DEFAULT_CURRENT_PAGE) {
						currentPage=Constant.DEFAULT_CURRENT_PAGE;
					}
				} catch (NumberFormatException e) {
					currentPage=Constant.DEFAULT_CURRENT_PAGE;
				}
			}
			if(pageSizeParam==null) {
				pageSize=Constant.DEFAULT_PAGE_SIZE;
			}else {
				try {
					pageSize=Integer.valueOf(pageSizeParam);
					if(pageSize<1) {
						pageSize=Constant.DEFAULT_PAGE_SIZE;
					}
				} catch (NumberFormatException e) {
					pageSize=Constant.DEFAULT_PAGE_SIZE;
				}
			}
			
			Page<Student> page=studentDao.list(currentPage, pageSize);
			return page;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Page<Student> search(String idParam,
			String nameParam,String sexParam,
			String telParam,String emailParam,
			String currentPageParam,String pageSizeParam) throws SQLException,TypeNotMatchException{
		try {
			//��֤�������,���Ϊ�������Ĭ��ֵ
			int currentPage;
			int pageSize;
			
			if(currentPageParam==null) {
				currentPage=Constant.DEFAULT_CURRENT_PAGE;
			}else {
				try {
					currentPage=Integer.valueOf(currentPageParam);
					if(currentPage<Constant.DEFAULT_CURRENT_PAGE) {
						currentPage=Constant.DEFAULT_CURRENT_PAGE;
					}
				} catch (NumberFormatException e) {
					currentPage=Constant.DEFAULT_CURRENT_PAGE;
				}
			}
			if(pageSizeParam==null) {
				pageSize=Constant.DEFAULT_PAGE_SIZE;
			}else {
				try {
					pageSize=Integer.valueOf(pageSizeParam);
					if(pageSize<1) {
						pageSize=Constant.DEFAULT_PAGE_SIZE;
					}
				} catch (NumberFormatException e) {
					pageSize=Constant.DEFAULT_PAGE_SIZE;
				}
			}
			
			Student searchModel =null;
			try {
				searchModel=StudentUtil.bindParam(idParam, nameParam, sexParam, telParam, emailParam);
			} catch (TypeNotMatchException e) {
				throw e;
			}
			
			Page<Student> page=studentDao.search(searchModel, currentPage, pageSize);
			return page;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public Student get(String idParam) throws SQLException,NullParamException,TypeNotMatchException {
		Long id;
		

		//��֤id
		try {
			id=StudentUtil.validId(idParam);
		} catch (NullParamException e) {
			throw e;
		} catch (TypeNotMatchException e) {
			throw e;
		}
		
		try {
			Student student=studentDao.get(id);
			return student;
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void add(String idParam,
			String nameParam,String sexParam,
			String telParam,String emailParam) throws SQLException,NullParamException,TypeNotMatchException,StudentRepeatException {
	
		try {
			//���
			StudentUtil.valid(idParam, nameParam, sexParam, telParam, emailParam);
			//�󶨲���
			Student student=StudentUtil.bindParam(idParam, nameParam, sexParam, telParam, emailParam);
			
			studentDao.add(student);
		} catch (NullParamException e) {
			throw e;
		} catch (TypeNotMatchException e) {
			throw e;
		} catch (MySQLIntegrityConstraintViolationException E) {
			throw new StudentRepeatException("��ѧ������Ϣ�Ѵ���");
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	public void delete(String idParam) throws SQLException,NullParamException,TypeNotMatchException{
		Long id;
		
		//��֤id
		try {
			id=StudentUtil.validId(idParam);
		} catch (NullParamException e) {
			throw e;
		} catch (TypeNotMatchException e) {
			throw e;
		}
		
		try {
			studentDao.delete(id);
		}  catch (SQLException e) {
			throw e;
		}
	}
	
	public void updeate(String idParam,
			String nameParam,String sexParam,
			String telParam,String emailParam) throws SQLException,NullParamException,TypeNotMatchException,NoOneParamException {
		
		//��֤id
		try {
			StudentUtil.validId(idParam);
		} catch (NullParamException e) {
			throw e;
		} catch (TypeNotMatchException e) {
			throw e;
		}
		
		Student student=null;
		try {
			student=StudentUtil.bindParam(idParam, nameParam, sexParam, telParam, emailParam);
		} catch (TypeNotMatchException e) {
			throw e;
		}
		
		//���û��һ�����µ���Ϣ,���׳��쳣
		if(student.getName()==null&&student.getSex()==null&&student.getTel()==null&&student.getEmail()==null) {
			throw new NoOneParamException("����Ҫ��дһ��Ҫ���µ���Ϣ");
		}
		
		try {
			studentDao.update(student);
		} catch (SQLException e) {
			throw e;
		}
	} 
}

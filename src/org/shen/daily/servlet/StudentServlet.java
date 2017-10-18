package org.shen.daily.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shen.daily.constant.Message;
import org.shen.daily.dto.Result;
import org.shen.daily.exception.NoOneParamException;
import org.shen.daily.exception.NullParamException;
import org.shen.daily.exception.StudentRepeatException;
import org.shen.daily.exception.TypeNotMatchException;
import org.shen.daily.model.Student;
import org.shen.daily.service.StudentService;


/**
 * @author ZhiqiangShen
 * ����Ե���ѧ���Ĳ���
 */
public class StudentServlet extends HttpServlet{

	private static final long serialVersionUID = 128402359626937058L;
	
	private StudentService studentService;
	
	
	/**
	 * ��studentService��ʼ��
	 * @throws ServletException
	 */
	@Override
	public void init() throws ServletException {
		this.studentService=new StudentService();
	}
	
	/* �����/Student/student ��get���� �Ի�ȡ����ѧ����Ϣ��������Ӧ
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ�������
		String idParam=req.getParameter("id");
		
		try {
			Student student=studentService.get(idParam);
			req.setAttribute("result", new Result<Student>(true,student));
		} catch (NullParamException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (TypeNotMatchException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("result", new Result<>(false, Message.GET_FAILURE));
			e.printStackTrace();
		}
		req.getRequestDispatcher("/jsp/detail.jsp").forward(req, resp);
		
	}

	/* �����/Student/student ��post���� ��������ѧ����Ϣ��������Ӧ
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String method=req.getParameter("_method");
		if(method!=null&&!method.equals("")) {
			if(method.equalsIgnoreCase("put")) {
				this.doPut(req, resp);
				return;
			}else if(method.equalsIgnoreCase("delete")){
				this.doDelete(req, resp);
				return;
			}
		}
		
		//��ȡ����
		String idParam=req.getParameter("id");
		String nameParam=req.getParameter("name");
		String sexParam=req.getParameter("sex");
		String telParam=req.getParameter("tel");
		String emailParam=req.getParameter("email");
		
		try {
			studentService.add(idParam, nameParam, sexParam, telParam, emailParam);
			req.setAttribute("result", new Result<>(true, Message.ADD_SUCCESS));
		} catch (NullParamException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (TypeNotMatchException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (StudentRepeatException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("result", new Result<>(false, Message.ADD_FAILURE));
			e.printStackTrace();
		}
		
		//������
		req.setAttribute("id", idParam);
		req.setAttribute("name", nameParam);
		req.setAttribute("sex", sexParam);
		req.setAttribute("tel", telParam);
		req.setAttribute("email", emailParam);
		
		req.getRequestDispatcher("/jsp/new.jsp").forward(req, resp);
		
	}

	/* �����/Student/student ��put���� �Ը���ѧ����Ϣ��������Ӧ
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ����
		String idParam=req.getParameter("id");
		String nameParam=req.getParameter("name");
		String sexParam=req.getParameter("sex");
		String telParam=req.getParameter("tel");
		String emailParam=req.getParameter("email");
		
		try {
			studentService.updeate(idParam, nameParam, sexParam, telParam, emailParam);
			req.setAttribute("result", new Result<>(true,1, Message.UPDATE_SUCCESS));
		} catch (NullParamException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (TypeNotMatchException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (NoOneParamException e) {
			req.setAttribute("result", new Result<>(false, e.getMessage()));
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("result", new Result<>(false, Message.UPDATE_FAILURE));
			e.printStackTrace();
		}
		
		//������
		req.setAttribute("id", idParam);
		req.setAttribute("name", nameParam);
		req.setAttribute("sex", sexParam);
		req.setAttribute("tel", telParam);
		req.setAttribute("email", emailParam);
		
		req.getRequestDispatcher("/jsp/detail.jsp").forward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idParam=req.getParameter("deleteId");
		try {
			studentService.delete(idParam);
			req.setAttribute("result", new Result<>(true,Message.DELETE_SUCCESS));
		} catch (NullParamException e) {
			req.setAttribute("result", new Result<>(false,e.getMessage()));
			e.printStackTrace();
		} catch (TypeNotMatchException e) {
			req.setAttribute("result", new Result<>(false,e.getMessage()));
			e.printStackTrace();
		} catch (SQLException e) {
			req.setAttribute("result", new Result<>(false,Message.DELETE_FAILURE));
			e.printStackTrace();
		}
		req.getRequestDispatcher("/students").forward(req, resp);
	}

	
	
	
}

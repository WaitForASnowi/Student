package org.shen.daily.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shen.daily.constant.Message;
import org.shen.daily.dto.Result;
import org.shen.daily.exception.TypeNotMatchException;
import org.shen.daily.model.Page;
import org.shen.daily.model.Student;
import org.shen.daily.service.StudentService;

/**
 * @author ZhiqiangShen
 * �����ѯѧ���б������
 */
public class ListServlet extends HttpServlet{
	
	private static final long serialVersionUID = -5175608261608688570L;
	private StudentService studetnService;
	
	
	/* ��studetnServiceʵ����
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {
		this.studetnService=new StudentService();
	}

	/* ��������/Student/student��get����,ֱ�ӵ���doPost����Ĭ�Ͻ��п�����
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doPost(req,resp);
	}

	/* ��������/Student/students��post������,������������Ӧ
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//��ȡ����
		String idParam=req.getParameter("id");
		String nameParam=req.getParameter("name");
		String sexParam=req.getParameter("sex");
		String telParam=req.getParameter("tel");
		String emailParam=req.getParameter("email");
		String currentPageParam=req.getParameter("currentPage");
		String pageSizeParam=req.getParameter("pageSize");
		try {
			Page<Student> page=studetnService.search(idParam, nameParam, sexParam, telParam, emailParam, currentPageParam, pageSizeParam);
			req.setAttribute("result", new Result<Page<Student>>(true,page));
		} catch (TypeNotMatchException e) {
			e.printStackTrace();
			req.setAttribute("result", new Result<Page<Student>>(false,e.getMessage()));
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("result", new Result<Page<Student>>(false,Message.LIST_FAILURE));
		}
		
		//����������л���
		req.setAttribute("id", idParam);
		req.setAttribute("name", nameParam);
		req.setAttribute("sex", sexParam);
		req.setAttribute("tel", telParam);
		req.setAttribute("email", emailParam);
		
		req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
	}
	
	
	
	
	
}

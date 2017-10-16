package org.shen.daily.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shen.daily.constant.Message;
import org.shen.daily.dto.Result;
import org.shen.daily.model.Page;
import org.shen.daily.model.Student;
import org.shen.daily.service.StudentService;

/**
 * @author ZhiqiangShen
 * 处理查询学生列表的请求
 */
public class StudentServlet extends HttpServlet{
	
	private static final long serialVersionUID = -5175608261608688570L;
	private StudentService studetnService;
	
	@Override
	public void init() throws ServletException {
		this.studetnService=new StudentService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String currentPageParam=req.getParameter("currentPage");
		String pageSizeParam=req.getParameter("pageSize");
		
		try {
			Page<Student> page=studetnService.list(currentPageParam, pageSizeParam);
			req.setAttribute("result", new Result<Page<Student>>(true, page));
			
		} catch (SQLException e) {
			e.printStackTrace();
			req.setAttribute("result", new Result<Page<Student>>(false, 1, Message.LIST_ERROR));
		}
		
		req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
	}
	
	
	
	
}

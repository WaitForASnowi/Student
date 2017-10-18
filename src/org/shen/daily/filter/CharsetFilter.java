package org.shen.daily.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpFilter;

/**
 * @author ZhiqiangShen
 * �ַ������������,��������
 */
public class CharsetFilter extends HttpFilter{
	private static final long serialVersionUID = -1106818031936766217L;
	
	//Ĭ���ַ�������
	private String charSet;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(this.charSet);
		response.setCharacterEncoding(this.charSet);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//��ʼ��Ĭ���ַ�������
		this.charSet=filterConfig.getInitParameter("charSet");
	}
	
	
}

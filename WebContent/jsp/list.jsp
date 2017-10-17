<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生列表</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<h2 class="text-center" style="margin-top:100px">学生列表</h2>

<!-- 学生列表  -->
<table class="table" style="margin-top:50px">

	<!-- 表头  -->
	<thead>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>手机号</th>
			<th>邮箱</th>
			<th>操作</th>
			<th>操作</th>
		</tr>
	</thead>
	
	<!-- 表格体 -->
	<tbody>
		<c:if test="${result.success}">
			<c:forEach items="${result.data.list}" var="student">
				<tr>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>
					<c:if test="${student.sex==1}">男</c:if>
					<c:if test="${student.sex==0}">女</c:if>
					</td>
					<td>${student.tel}</td>
					<td>${student.email}</td>
					<td>
						<button type="button" class="btn btn-default">查看</button>
					</td>
					<td>
						<button type="button" class="btn btn-default">删除</button>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>

<!-- 分页按钮 -->
<c:if test="${result.success}">
	<ul class="pager" style="margin-top:100px">
		<li><a href="<%=basePath%>/students?currentPage=1&pageSize=${result.data.pageSize}">首页</a></li>
		<c:forEach begin="0" end="${result.data.pageNum-1}" varStatus="status">
			<li><a href="<%=basePath%>/students?currentPage=${status.count}&pageSize=${result.data.pageSize}">${status.count}</a></li>
		</c:forEach>
		<li><a href="<%=basePath%>/students?currentPage=${result.data.pageNum}&pageSize=${result.data.pageSize}">尾页</a></li>
	</ul>
</c:if>

</body>
</html>
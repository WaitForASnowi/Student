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
<h2 class="text-center">学生列表</h2>
<table class="table">
	<thead>
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>性别</th>
			<th>手机号</th>
			<th>邮箱</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${result.success}">
			<c:forEach items="${result.data.list}" var="student">
				<tr>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.sex}</td>
					<td>${student.tel}</td>
					<td>${student.email}</td>
				</tr>
			</c:forEach>
		</c:if>
	</tbody>
</table>
<c:if test="${result.success}">
	<ul class="pagination text-center">
		<c:forEach begin="0" end="${result.data.pageNum-1}" varStatus="status">
			<li><a href="<%=basePath%>/student?currentPage=${status.count}&pageSize=${result.data.pageSize}">${status.count}</a></li>
		</c:forEach>
	</ul>
</c:if>

</body>
</html>
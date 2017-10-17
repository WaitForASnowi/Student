<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到学生信息管理系统</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/index.css"/>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#button").click(function(){
			location.href="<%=basePath%>/students";
		});
	})
</script>
</head>
<body>
<div>
	<h2>学生管理系统</h2>
	<button id="button"
	type="button" class="btn btn-primary btn-lg">进入
	</button>

</div>
</body>
</html>
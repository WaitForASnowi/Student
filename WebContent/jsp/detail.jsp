<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String basePath=request.getContextPath();
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
<title>学生详细</title>
</head>
<body>
<h2 class="text-center" style="margin-top:100px">学生详细</h2>
	<form class="form-horizontal" style="margin-top:50px">
		<div class="form-group">
			<label class="control-label col-sm-5">学号</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" disabled="disabled"/>
			</div>
		</div> 
		<div class="form-group">
			<label class="control-label col-sm-5">姓名</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-5">性别</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-5">手机号</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" disabled="disabled">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-5">邮箱</label>
			<div class="col-sm-2">
				<input type="text" class="form-control" disabled="disabled">
			</div>
		</div>
	</form>
		<button class="btn btn-default center-block" style="margin-top:50px">修改</button>
	

</body>
</html>
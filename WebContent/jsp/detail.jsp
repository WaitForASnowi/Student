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
<link rel="stylesheet" type="text/css" href="<%=basePath %>/css/bootstrap.css"/>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.2.1.min.js"></script>
<title>学生详细</title>
<script type="text/javascript">
	$(function(){
		$("button").click(function(){
			$("#id").removeAttr("disabled");
			$("#form").submit();
		});
	});
</script>
</head>
<body>
<h2 class="text-center" style="margin-top:100px">学生详细</h2>
	
		<form class="form-horizontal" style="margin-top:50px" action="<%=basePath%>/student" method="post" id="form">
			<input type="hidden" name="_method" value="put">
			<div class="form-group">
				<label class="control-label col-sm-5">学号</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" disabled="disabled" id="id" name="id" value="${result.data.id}"/>
				</div>
			</div> 
			<div class="form-group">
				<label class="control-label col-sm-5">姓名</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="name" name="name" value="${result.data.name }">
				</div>
			</div>
			<div class="form-group">
				
				<label class="control-label col-sm-5">性别</label>
				<div class="col-sm-2">
					<select class="form-control" id="sex" name="sex" >
						<c:choose>
							<c:when test="${! empty result.data}">
							<c:choose>
								<c:when test="${result.data.sex eq 1}">
								<option id="male" selected="selected"  value="1">男</option>
								<option id="female"  value="0">女</option>
								</c:when>
								<c:when test="${result.data.sex eq 0}">
								<option id="male"  value="1">男</option>
								<option id="female" selected="selected" value="0">女</option>
								</c:when>
							</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
								<c:when test="${requestScope.sex eq 1}">
								<option id="male" selected="selected"  value="1">男</option>
								<option id="female"  value="0">女</option>
								</c:when>
								<c:when test="${requestScope.sex eq 0}">
								<option id="male"  value="1">男</option>
								<option id="female" selected="selected" value="0">女</option>
								</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-5">手机号</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="tel" name="tel" value="${result.data.tel }">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-5">邮箱</label>
				<div class="col-sm-2">
					<input type="text" class="form-control" id="email" name="email" value="${result.data.email }">
				</div>
			</div>
			<button type="button" class="btn btn-default center-block" style="margin-top:50px">修改</button>
		</form>
	
	<c:if test="${empty result.data}">
		<script type="text/javascript">
			$(function(){
				var id=$("#id");
				var name=$("#name");
				var sex=$("#sex");
				var tel=$("#tel");
				var email=$("#email");
				id.val("${requestScope.id}");
				name.val("${requestScope.name}");
				tel.val("${requestScope.tel}");
				email.val("${requestScope.email}");
			});
		</script>
	</c:if>
	<c:if test="${! empty result.message }">
		<script type="text/javascript">
			alert("${result.message}");
		</script>
	</c:if>
	
	<c:if test="${result.success&&!empty result.code&&result.code==1}">
		<script type="text/javascript">
			location.href="<%=basePath%>/students";
		</script>
	</c:if>

</body>
</html>
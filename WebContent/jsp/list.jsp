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
<script type="text/javascript">
	$(function(){
		var searchRow=$("#searchRow");
		var searchButton=$("#searchButton");
		
		searchButton.click(function(){
			searchRow.toggle();
			if(searchRow.is(":hidden")){
				searchButton.text("显示搜索");
			}else{
				searchButton.text("隐藏搜索");
			}
		});
		
		$("#addButton").click(function(){
			location.href="<%=basePath%>/jsp/new.jsp";
		});
	});
	
	function search(currentPage,pageSize){
		$("#currentPage").val(currentPage);
		$("#pageSize").val(pageSize);
		$("#searchForm").submit();
	}
	
	function get(id){
		var getForm =$("#getForm");
		getForm.children("input").val(id);
		getForm.submit();
		
	}
	
	function fdelete(id,currentPage,pageSize){
		var flag=confirm("确定删除");
		if(flag){
			var deleteForm=$("#deleteForm");
			$("#deleteId").val(id);
			$("#dcurrentPage").val(currentPage);
			$("#dpageSize").val(pageSize);
			var id=$("#id").val();
			$("#dId").val(id);
			var name=$("#name").val();
			$("#dName").val(name);
			var sex=$("#sex").val();
			$("#dSex").val(sex);
			var tel=$("#tel").val();
			$("#dTel").val(tel);
			var email=$("#email").val();
			
			$("#dEmail").val(email);
			deleteForm.submit();
		}
	}

</script>
</head>
<body>
<h2 class="text-center" style="margin-top:100px">学生列表</h2>
<div class="text-right" style="margin-right:30px">
	<button id="searchButton" class="btn btn-default" style="margin-right:20px">隐藏搜索</button>
	<button class="btn btn-default" id="addButton">新增</button>
</div>
<form id="deleteForm" action="<%=basePath%>/student" method="post">
	<input type="hidden" name="_method" value="delete">
	<input type="hidden" id="deleteId" name="deleteId" >
	<input type="hidden" id="dcurrentPage" name="currentPage">
	<input type="hidden" id="dpageSize" name="pageSize">
	<input type="hidden" id="dId" name="id">
	<input type="hidden" id="dMame" name="name">
	<input type="hidden" id="dSex" name="sex">
	<input type="hidden" id="dTel" name="tel">
	<input type="hidden" id="dEmail" name="email">
</form>
<!-- 学生列表  -->
<form id="searchForm" action="<%=basePath%>/students" method="post">
	<input type="hidden" id="currentPage" name="currentPage">
	<input type="hidden" id="pageSize" name="pageSize">
	<table class="table" style="margin-top:50px">
		<!-- 表头  -->
		<thead>
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>手机号</th>
				<th>邮箱</th>
				<th></th>
				<th></th>
			</tr>
		</thead>
	
		<!-- 表格体 -->
		<tbody>
			<tr id="searchRow">
				<td><input class="form-control" type="text" id="id" name="id" value="${requestScope.id}"/></td>
				<td><input class="form-control" type="text" id="name" name="name" value="${requestScope.name}"/></td>
				<td>
				<select class="form-control" id="sex" name="sex">
					<c:choose>
						<c:when test="${requestScope.sex==1}">
							<option></option>
							<option value="0">女</option>
							<option value="1"selected="selected">男</option>
						</c:when>
						<c:when test="${empty requestScope.sex}">
							<option selected="selected"></option>
							<option value="0">女</option>
							<option value="1">男</option>
						</c:when>
						<c:otherwise>
							<option></option>
							<option value="0" selected="selected">女</option>
							<option value="1">男</option>
						</c:otherwise>
					</c:choose>
					
					
				</select>
				</td>
				<td><input class="form-control" type="text" id="tel" name="tel" value="${requestScope.tel}"/></td>
				<td><input class="form-control" type="text" id="email" name="email" value="${requestScope.email}"/></td>
				<td>
					<button type="submit" class="btn btn-default">搜索</button>
				</td>
			</tr>
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
							<button type="button" class="btn btn-default" onclick="get(${student.id})">查看</button>
						</td>
						<td>
							<button type="button" class="btn btn-default" onclick="fdelete(${student.id},${result.data.currentPage},${result.data.pageSize})">删除</button>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</form>


<!-- 分页按钮 -->
<c:if test="${result.success}">
	<ul class="pager" style="margin-top:100px">
		<li onclick="search(1,${result.data.pageSize})"><a href="#">首页</a></li>
		<c:forEach begin="0" end="${result.data.pageNum-1}" varStatus="status">
			<li onclick="search(${status.count},${result.data.pageSize })"><a  href="#">${status.count}</a></li>
		</c:forEach>
		<li onclick="search(${result.data.pageNum},${result.data.pageSize})"><a href="#">尾页</a></li>
	</ul>
</c:if>


<form id="getForm" action="<%=basePath%>/student" method="get">
	<input type="hidden" name="id">
</form>

<c:if test="${! empty result.message }">
	<script type="text/javascript">
		alert("${result.message}");
	</script>
</c:if>
</body>
</html>
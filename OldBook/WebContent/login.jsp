<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*"
    pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>竹帛网--login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
</head>
<body>
<div class="container-fluid">
  <div class="row" style="padding-top:12%">
  	<div class="col-md-2"></div>
  	<div class="col-md-4">
  		<img src="${pageContext.request.contextPath}/images/login_img.jpg" alt="book" class="img-circle" style="width:100%;height:auto"/>
  	</div>
  	<div class="col-md-1"></div>
    <div class="panel panel-info col-md-4" >
		<div class="panel-heading">LOGIN</div>
		<div class="panel-body">
		<form method="POST" action="login">
			<div class="input-group" style="width:100%;margin-bottom:20px;">
  				<span class="input-group-addon" style="width:20%;">用户名：</span>
  				<input style="width:100%;" type="text" class="form-control" name="userName" id="userName" placeholder="userName" value="${requestScope.userName}" aria-describedby="basic-addon1" required>
			</div>
			<div class="input-group" style="width:100%;margin-bottom:20px;">
  				<span class="input-group-addon" style="width:20%;">&nbsp;密&nbsp;码&nbsp;：</span>
  				<input style="width:100%;" type="password" class="form-control" name="password" id="password" placeholder="password" value="${requestScope.password }" aria-describedby="basic-addon1" required>
			</div>
			<div class="input-group" style="width:100%;margin-bottom:20px;">
  				<span class="input-group-addon" style="width:20%;">验证码：</span>
  				<input style="width:40%;" type="text" class="form-control" name="checkCode" id="checkCode" value="${requestScope.checkCode }" aria-describedby="basic-addon1" required>
  				<div class="input-group-addon" style="width:60%;float:left">
  					<img src="checkCode" alt="checkpicture" id="checkpicture" style="width:50%;float:left"/>
  					<a href="#" style="width:50%;font-style:italic" onclick="changeCheckCode();">换一个</a>
  				</div>
			</div>
			<div class="input-group" style="width:100%;" >
				<input type="submit" style="width:100%" class="btn btn-primary" value="登录"/>
			</div>
			<span style="float:right" class="btn btn-link"><a href="register.jsp" title="转到注册页面">立即注册</a></span>
		</form>
		</div>
	</div>
	<div class="col-md-1"></div>
  </div>
</div>
	
	
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
 	<script>
		function changeCheckCode(){
			var img=document.getElementById("checkpicture");
			img.src="checkCode?time="+Math.random();
		}
	</script>
	<% if(request.getAttribute("error")!=null&&!request.getAttribute("error").equals("")){ %>
	<script>
		alert("${requestScope.error}");
	</script>
	<%} %>
</body>
</html>
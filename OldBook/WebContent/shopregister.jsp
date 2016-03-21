<%@ page language="java" contentType="text/html; charset=utf-8"
	import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>竹帛网--register</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
	
<style type="text/css">
	.errormsg {
		font-size:0.6em;
		color:red;
	}
</style>
	
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script
	src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
<script>
	$(function() {
		$("#checkpicture").next().click(changeCheckCode);
		$("#loginName").blur(isShopExisted);
		$("#passwordAgain").blur(isPasswordSame)
	});
	function changeCheckCode() {
		var img = document.getElementById("checkpicture");
		img.src = "checkCode?time=" + Math.random();
	}
	//判断用户名是否存在
	function isShopExisted(){
		var text=$("#loginName").val();
		//alert(text);
		$.ajax({
			type:"GET",
			url:"shopregister?type=checkloginName&loginName="+text,
			dataType:"text",
			success:function(data){
				$("#userInfo").html(data);
			}
		});
	}
	//判断两次输入的密码是否相同
	function isPasswordSame(){
		if($(this).val()!=$("#loginPassword").val()){
			$("#passwordInfo").html("密码有误");
			$(this).val("");
			$("#loginPassword").val("");
		}else{
			$("#passwordInfo").html("");
		}
	}
</script>
</head>
<body>
	<div class="container-fluid">
		<div class="row" style="padding-top: 5%">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="panel panel-success">
					<div class="panel-heading">请填写下列信息进行注册:</div>
					<div class="panel-body">
						<form class="form-horizontal" action="ShopRegisterServlet" method="POST">
							<div class="form-group">
								<label for="loginName" class="col-sm-3 control-label">用户名</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="loginName"
										id="loginName" value="${requestScope.loginName }" placeholder="如:lisi" required>
								</div>
								<div class="col-sm-2">
									<span id="userInfo" class="errormsg"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="loginPassword" class="col-sm-3 control-label">密码</label>
								<div class="col-sm-7">
									<input type="loginPassword" class="form-control" name="loginPassword"
										id="loginPassword" value="${requestScope.loginPassword }" placeholder="" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							<div class="form-group">
								<label for="passwordAgain" class="col-sm-3 control-label">再次输入密码</label>
								<div class="col-sm-7">
									<input type="password" class="form-control" placeholder=""
										id="passwordAgain"
										required>
								</div>
								<div class="col-sm-2">
									<span id="passwordInfo" class="errormsg"></span>
								</div>
							</div>
							<div class="form-group">
								<label for="email" class="col-sm-3 control-label">邮箱</label>
								<div class="col-sm-7">
									<input type="email" class="form-control" name="email"
										id="email" value="${requestScope.email }" placeholder="如:lisi@163.com" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							<div class="form-group">
								<label for="phoneNumber" class="col-sm-3 control-label">电话号码</label>
								<div class="col-sm-7">
									<input type="tel" class="form-control" name="phoneNumber"
										id="phoneNumber" value="${requestScope.phoneNumber }" placeholder="如:18814122715" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							<div class="form-group">
								<label for="realName" class="col-sm-3 control-label">真实姓名</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="realName"
										id="realName" value="${requestScope.realName }" placeholder="如:李四" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							<div class="form-group">
								<label for="identity" class="col-sm-3 control-label">身份证号</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="identity"
										id="identity" value="${requestScope.identity }" placeholder="" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							
							<div class="form-group">
								<label for="address" class="col-sm-3 control-label">地址</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="address"
										id="address" value="${requestScope.address }" placeholder="" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							<div class="form-group">
								<label for="shopName" class="col-sm-3 control-label">店铺名称</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" name="shopName"
										id="shopName" value="${requestScope.shopName }" placeholder="" required>
								</div>
								<div class="col-sm-2">
								</div>
							</div>
							
							
							<div class="form-group">
								<label for="checkCode" class="col-sm-3 control-label">验证码</label>
								<div class="col-sm-3">
									<input type="text" class="form-control" name="checkCode"
										id="checkCode" value="${requestScope.checkCode }" placeholder="" required>
								</div>
								<div class="col-sm-4">
									<img src="checkCode" alt="checkpicture" id="checkpicture" /> <a
										href="#">换一个</a>
								</div>
								<div class="col-sm-1">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-8">
									<button type="submit" class="btn btn-default">提交</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>
	
	
	<% if(request.getAttribute("error")!=null&&!request.getAttribute("error").equals("")){ %>
	<script>
		alert("${requestScope.error}");
	</script>
	<%} %>
</body>
</html>
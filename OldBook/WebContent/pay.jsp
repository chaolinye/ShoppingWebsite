<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link
	href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<style type="text/css">
#payMoney {
	height: 100px;
	text-align: center;
	font-size: 1.5em;
	padding-top: 40px;
}

#payMethod {
	text-align: center;
}

#next {
	margin-top:10px;
}
</style>
</head>
<body>
	<div class="container-fluid" style="padding-left: 0px;">
		<div class="row">
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-md-10 col-sm-10">
				<div class="panel panel-default" id="payMoney">
					支付金额:<span>￥10.0</span>
				</div>
			</div>
			<div class="col-md-1 col-sm-1"></div>
		</div>
		<div class="row">
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-md-10 col-sm-10">
				<div class="panel panel-default">
					<div class="panel-heading">选择支付方式</div>
					<div class="panel-body" id="payMethod">
						<div class="row">
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="gong">中国工商银行
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="zhao">中国招商银行
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="nong">中国农业银行
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="gong2">中国工商银行
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="zhao2">中国招商银行
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="nong2">中国农业银行
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="gong3">中国工商银行
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="zhao3">中国招商银行
							</div>
							<div class="col-md-4 col-sm-4">
								<input type="radio" name="payMethod" value="nong3">中国农业银行
							</div>
						</div>
						<div class="row">
							<div class="col-md-4 col-sm-4"></div>
							<div class="col-md-4 col-sm-4"></div>
							<div class="col-md-4 col-sm-4">
								<a class="btn btn-default" href="#" title="跳到外部银行支付界面" id="next">下一步</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-1 col-sm-1"></div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
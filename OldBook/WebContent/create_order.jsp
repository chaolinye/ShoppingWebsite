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
	div#tableContainer{
		display:table;
		float:right;
	}
	div#table > div{
		display:table-row;
	} 
	div#table > div > div{
		display:table-cell;
	}
</style>
</head>
<body>
<div class="container-fluid" style="padding-left: 0px;">
	<div class="row">
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<div class="panel panel-default">
			<div class="panel-heading">订单已生成</div>
			<div class="panel-body">
				<img src="#" alt="ok">
				<div id="tableContainer">
					<div>
						<div>
							订单编号
						</div>
						<div>
							123124234234
						</div>
					</div>
					<div>
						<div>
							合计金额
						</div>
						<div>
							￥24.0
						</div>
					</div>
					<div>
						<div>
							收货地址
						</div>
						<div>
							广州大学城
						</div>
					</div>
					<div>
						<div>
							收货地址
						</div>
						<div>
							广州大学城
						</div>
					</div>
					<div>
						<div>
						</div>
						<div>
							<a href="#" title="xxx">
								支付														
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-md-2"></div>
	</div>
</div>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
%>
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
	#bookImage {
		width:100%;
		height:auto;
	}
	#msgPanel {
		text-align:center;
		vertical-align: middle;
	}
</style>
</head>
<body>
	<div class="container-fluid" style="padding-left: 0px;">
		<div class="row">
			<div class="col-md-5 col-sm-5">
				<img src="searching?type=Mimg&&book=<%=request.getParameter("book")%>" alt="book_image" id="bookImage">
			</div>
			<div class="col-md-1 col-sm-1"></div>
			<div class="col-md-6 col-sm-6">
				<div class="panel panel-default" id="msgPanel">
					<div class="panel-heading">图书详细信息</div>
					<div class="panel-body">
						<span>书名：</span><span><%=session.getAttribute("Xname")%></span><br>
						<span>作者：</span><span><%=session.getAttribute("Xauthor")%></span><br>
						<span>单价：</span><span><%=session.getAttribute("Xprice")%></span><br>
						<span>余量：</span><span><%=session.getAttribute("Xamount")%></span><br>
						<span>出版社：</span><span><%=session.getAttribute("Xpress")%></span><br>
						<span>出版日期：</span><span><%=session.getAttribute("Xpressd")%></span><br>
						<span>店铺：</span><span><%=session.getAttribute("Xshop") %></span><br>
						<span>店铺信誉：</span><span><%=session.getAttribute("Xcredit")%></span><br>
						<span>热度：</span><span><%=session.getAttribute("Xhot")%></span><br>
						<span>简介：</span><span><%=session.getAttribute("Xsy")%></span><br>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-10 col-sm-10"></div>
			<div class="col-md-2 col-sm-2">
			<a id="addCart" class="btn btn-default" href="shopcart?action=addItem&amount=1&bid=${sessionScope.Xbid}" role="button">加入购物车</a>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function add(){
		<%if(session.getAttribute("user")==null){%>
			alert('请先登录');
			return false;
		<%}%>
		return true;
	}
	document.getElementById('addCart').onclick=add;
</script>

	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
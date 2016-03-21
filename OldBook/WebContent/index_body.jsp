<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="org.freedom.model.*,org.freedom.service.*"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
</head>
<body>
<%SearchingService ser=new SearchingService();
Book book=null;%>
	<div class="container-fluid" style="padding-left: 0px;">
		<div class="row">
			<div class="col-md-8 col-sm-8">
				<div id="carousel-example-generic" class="carousel slide"
					data-ride="carousel" style="width: 100%;">
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0"
							class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"
							class=""></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"
							class=""></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="item next left">
							<img data-src="holder.js/900x500/auto/#777:#555/text:First slide"
								alt="First slide [900x500]"
								src="images/163.jpg"
								data-holder-rendered="true"
								style="width:100%;height:auto">
						</div>
						<div class="item">
							<img
								data-src="holder.js/900x500/auto/#666:#444/text:Second slide"
								alt="Second slide [900x500]"
								src="images/164.jpg"
								data-holder-rendered="true"
								style="width:100%;height:auto">
						</div>
						<div class="item active left">
							<img data-src="holder.js/900x500/auto/#555:#333/text:Third slide"
								alt="Third slide [900x500]"
								src="images/167.jpg"
								data-holder-rendered="true"
								style="width:100%;height:auto">
						</div>
					</div>
					<a class="left carousel-control" href="#carousel-example-generic"
						role="button" data-slide="prev"> <span
						class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic"
						role="button" data-slide="next"> <span
						class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
				</div>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">最新入库</div>
					<div class="panel-body">
					<%book=ser.findLatestbook(); 
					if(book!=null){%>
						<div class="well well-lg"><%=book.getBookName() %></div>
						<%for(int i=0;i<2;i++){
						book=ser.findNextBook();
						if(book!=null){%>
						<div class="well well-lg"><%=book.getBookName() %></div>
						<%}} 
						book=null;
						ser.closeService();}%>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-8 col-sm-8">
				<div class="panel panel-default">
					<div class="panel-heading">猜你喜欢</div>
					<div class="panel-body">
						<div class="row">
						<%User user=(User)session.getAttribute("user");
						if(user!=null)
						book=ser.findbookYouMayLike(user.getUid());
						if(book!=null)
						{
						for(int i=0;i<8;i++){%>
							<div class="col-md-3 col-sm-3">
								<a href="#" class="thumbnail"> <img src="order?type=img&&bbpath=<%=book.getImage() %>" alt="...">
								</a>
							</div>
						<%book=ser.findNextBook();
						if(book==null)break;
						}
						ser.closeService();
						book=null;
						}else{
						%>
						<div >然而我并不知道你喜欢什么书。。。买本书或许我就能猜到了呢？</div>
					<%} %>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4 col-sm-4">
				<div class="panel panel-default">
					<div class="panel-heading">热度排行</div>
					<div class="panel-body">
						<ul class="list-group">
						<%book=ser.findbookPopular();
						if(book!=null)
						{
						for(int i=0;i<10;i++){%>
							<li class="list-group-item"><%=book.getBookName() %></li>
						<% book=ser.findNextBook();
						if(book==null)break;
						}
						ser.closeService();
						book=null;
						}%>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="org.freedom.model.*,org.freedom.service.*"%>
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
img {
	width:30%;
	height:auto;
}

.row {
	margin-bottom:20px;
}

.centerDiv {
	padding-top:30px;
}
.container {
	padding-top:10px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-sm-4">图书</div>
			<div class="col-md-2 col-sm-2">金额</div>
			<div class="col-md-4 col-sm-4">订单状态</div>
			<div class="col-md-2 col-sm-2">操作</div>			
		</div>
		<% int uid=Integer.parseInt(request.getParameter("ouid"));
		int ppage=Integer.parseInt(request.getParameter("opage"));
		OrderService oser=new OrderService();
		OrderItemService iser=new OrderItemService();
		Order order=oser.findOrderByUser(uid);
		for(int i=0;i<ppage*3;i++)order=oser.findNextOrder();
		for(int k=0;k<3;k++)
		{
			float sum=0;
			if(order==null) break;
			if(order.getStatus()==0)
			{%>				
		<div class="row panel panel-default">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					订单号:<span><%=order.getOid()%></span>
				</div>
				<div class="col-md-8 col-sm-8">
					下单时间:<span><%=order.getTime()%></span>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-4">
				<%OrderItem item=iser.findItemByOrder(order.getOid())	;
				while(item!=null)
				{
					Book book=item.findBook();
					sum=sum+book.getPrice(); 
					%>
				
					<img src="order?type=img&&bbpath=<%=book.getImage() %>"> 
					<%
					item=iser.findNextItem();		
				} %>
				</div>
				<div class="col-md-2 col-sm-2 centerDiv">共计：<%=sum %>元</div>
				<div class="col-md-4 col-sm-4 centerDiv">(等待收货)</div>
				<div class="col-md-2 col-sm-2">
					<a href="#" title="check">查看</a><br> 
					<a href="#" title="check">确认收货</a><br>
				</div>
			</div>
		</div>
		<%
		iser.closeService();
		order=oser.findNextOrder();
		System.out.println("执行了一次下一次");
		if(order==null)break;
		}else { %>
		<div class="row panel panel-default">
			<div class="row">
				<div class="col-md-4 col-sm-4">
					订单号:<span><%=order.getOid()%></span>
				</div>
				<div class="col-md-8 col-sm-8">
					下单时间:<span><%=order.getTime()%></span>
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-sm-4">
				<%OrderItem item=iser.findItemByOrder(order.getOid());
				while(item!=null)
				{
					Book book=item.findBook();
					sum=sum+book.getPrice();
					%>			
					<img src="order?type=img&&bbpath=<%=book.getImage() %>"> 
					<%
				item=iser.findNextItem();	
				} %>
				</div>
				<div class="col-md-2 col-sm-2 centerDiv">共计：<%=sum%>元</div>
				<div class="col-md-4 col-sm-4 centerDiv">(已收货)</div>
				<div class="col-md-2 col-sm-2">
					<a href="#" title="check">查看</a><br>
				</div>
			</div>
		</div>
		<%
		iser.closeService();
		order=oser.findNextOrder();
		System.out.println("又执行了一次下一次");
		if(order==null)break;
		}
		} 
		oser.closeService();%>
		<div class="row">
			<div class="col-md-7"></div>
			<div class="col-md-4">
				<nav>
					<ul class="pagination">
						<li><a href="#" aria-label="Previous"> <span
								aria-hidden="true">&laquo;</span>
						</a></li>
						<%int pagesum=Integer.valueOf(request.getParameter("orderpagesum"));
						for(int i=0;i<pagesum;i++)
						{%>
						<li><a href="order?type=changepage&&ppage=<%=String.valueOf(i)%>"><%=i+1%></a></li>
						<%} %>
						<li><a href="#" aria-label="Next"> <span
								aria-hidden="true">&raquo;</span>
						</a></li>
					</ul>
				</nav>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-1" > <a href="index.jsp" >返回主页</a></div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
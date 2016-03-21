<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@  page import="java.util.*"  import="org.freedom.model.*"%>
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
	#sum {
		float:right;
	}
	#address {
		width:100%;
		clear:both;
		border-top: 1px solid gray;
		border-bottom:1px solid gray;
	}
	#createOrder {
		display:block;
		float:right;
		clear:both;
		margin-top:10px;
	}
</style>
</head>
<body>
		<div class="panel panel-default">
			<div class="panel-heading">订单</div>
			<%
			request.setCharacterEncoding("Gb2312");
			String path=request.getContextPath();
			Collection<Order> orders=(Collection<Order>)request.getSession().getAttribute("orders");
			if(orders==null||orders.size()<=0){
				out.println("订单查询失败~!");
			%>
			<br><a href="<%=path%>/shopcart?action=showcart">返回购物车</a>
			<%
			    return;
			}
			Iterator<Order> i_order=orders.iterator();
			%>
			<table class="table">
				<tr>
					<th>订单名称</th>
					<th>时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
				int i=0;
				String strStatus=null;
				while(i_order.hasNext()){
					Order order=(Order)i_order.next();
					int orderId=order.getOid();
					int status=order.getStatus();
					if(status==0) strStatus="正在进行中...";
					else strStatus="订单已完成";
				%>
				<tr>
					<td><%=orderId %></td>
					<td><%=order.getTime() %></td>
					<td><%=strStatus %></td>
					<td><a href="#">订单详情</a>
				</tr>
				<%
				    i++;}
				%>
			</table>
			<div class="panel-body">
			<a href="<%=path%>/shopcart?action=showcart">返回购物车</a>
			&nbsp;&nbsp;&nbsp;
			<a href="index_body.jsp">继续购物</a>
			</div>
		</div>

	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
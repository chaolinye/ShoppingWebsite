<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@  page import="java.util.*"  import="org.freedom.model.*"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<script type="text/javascript" >
      function select_all(){
    	  var ss=ordercart.oitemNo;
    	  for(var i=0;i<ss.length;i++){
    		  if(ordercart.oItem_all.checked) ss[i].checked=true;
    		  else ss[i].checked=false;
    		  }
    	  }
</script>
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
			<div class="panel-heading">生成订单</div>
			<%
			request.setCharacterEncoding("Gb2312");
			String path=request.getContextPath();
			Collection<CartItem> items=(Collection<CartItem>)request.getSession().getAttribute("items");
			if(items==null||items.size()<=0){
				out.println("取消订单!");
			%>
			<br><a href="<%=path%>/shopcart?action=showcart">返回购物车</a>
			<%
			    return;
			}
			Iterator<CartItem> item=items.iterator();
			%>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="<%=path %>/balance">查看所有订单</a>
			<form action="<%=path %>/balance" name="ordercart" method="post">
			<table class="table">
				<tr>
					<th><input type="checkbox" name="oItem_all" value="0" onclick="select_all()">全选</th>
					<th>图书名称</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
					<th>操作</th>
				</tr>
				<%
				int i=0;
				float sum=0;
				while(item.hasNext()){
					CartItem cartitem=(CartItem)item.next();
					int cartItemId=cartitem.getCartItemId();
				%>
				<tr>
				<th><input type="checkbox" name="oitemNo" value=<%=cartItemId %>></th>
					<td><%=cartitem.getName() %></td>
					<td>￥<%=cartitem.getPrice() %></td>
					<td><%=cartitem.getAmount() %></td>
					<td><span>￥<%=cartitem.getAmount()*cartitem.getPrice() %></span></td>
					<td><a href="<%=path%>/balance?action=deleteoItem&&
					orderItemId=<%=cartItemId%>">删除</a></td>
				</tr>
				<%
				    sum+=cartitem.getAmount()*cartitem.getPrice();
				    i++;
				}
				%>
			</table>
			<div class="panel-body">
				<div id="sum">总计:<span>￥<%=sum %></span></div>
				<div id="address">收货地址：<address>广州番禺大学城华南理工大学C12</address></div>
				<button id="createOrder" name="action" value="confirm">生成订单</button>
			</div>
			</form>
		</div>


	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
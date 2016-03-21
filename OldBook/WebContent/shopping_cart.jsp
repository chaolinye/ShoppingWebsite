<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.*"  import="org.freedom.model.CartItem" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%
     request.setCharacterEncoding("Gb2312");
     String path=request.getContextPath();
     float sum=0;
%>
<script type="text/javascript" >
      function select_all(){
    	  var ss=shoppingcart.itemNo;
    	  for(var i=0;i<ss.length;i++){
    		  if(shoppingcart.cart_all.checked) ss[i].checked=true;
    		  else ss[i].checked=false;
    		  }
    	  }
      function saveShopCart(){
    	  var th=document.shoppingcart; 
    	  var basePath="shopcart?action=saveCart";
    	  if(th.itemNo.length){
    			  if(confirm("确认要保存商品信息？")){
    				  th.action=basePath;
    				  th.submit();}
    		  }
      }
      function deleteShopCart(){
    	  var th=document.shoppingcart; 
    	  var basePath="shopcart?action=deleteCart";
    	  var sum=0;
    	  if(th.itemNo.length){
    		  for(var i=0;i<th.itemNo.length;i++){
    				  if(th.itemNo[i].checked==true) sum=sum+1;
    		  }
    		  if(sum==0){
    			  alert("最少选择一项!");
    			  return;}
    		  else{
    			  if(confirm("确认要成批删除选中商品？")){
    				  th.action=basePath;
    				  th.submit();}
    			  }
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
	#gotoBalance {
		display:block;
		float:right;
		clear:right;
	}
</style>
</head>
<body>
		<div class="panel panel-default">
			<div class="panel-heading">购物车</div>
			<%
				Collection<CartItem> cart=(Collection<CartItem>)request.getSession().getAttribute("shopcart");
				if(cart==null||cart.size()<=0){
					out.println("购物车中暂时没有商品~");
				%>
				<br><a href="index_body.jsp">继续购物</a>
				<%
				    return;
				}
				Iterator<CartItem> iCart=cart.iterator();
				%>
			<form action="<%=path %>/balance" name="shoppingcart" method="post">
			<table class="table">
				<tr>
					<th><input type="checkbox" name="cart_all" value="0" onclick="select_all()">全选</th>
					<th>图书名称</th>
					<th>单价</th>
					<th>数量</th>
					<th>小计</th>
					<th>操作</th>
				</tr>
				<%
				int i=0;
				while(iCart.hasNext()){
					CartItem cartitem=(CartItem)iCart.next();
					int cartItemId=cartitem.getCartItemId();
					String fieldNum="num_"+i;
					String fieldBook="book_"+i;
				%>
				<tr>
				<th><input type="checkbox" name="itemNo" value=<%=cartItemId %>></th>
					<td><%=cartitem.getName() %></td>
					<td>￥<%=cartitem.getPrice() %></td>
					<td><input type="number" name="<%=fieldNum %>"
					 value="<%=cartitem.getAmount()%>"/>
					 <input type="hidden" name="<%=fieldBook %>"  
					 value="<%=cartitem.getBid()%>"/></td>
					<td><span>￥<%=cartitem.getAmount()*cartitem.getPrice() %></span></td>
					<td><a href="<%=path%>/shopcart?action=deleteItem&&
					cartItemId=<%=cartItemId%>">删除</a></td>
				</tr>
				<%
				    sum+=cartitem.getAmount()*cartitem.getPrice();
				    i++;
				}
				%>
			</table>
			<div class="panel-body">
				<a href="javascript:void();" onclick="saveShopCart();" title="save">保存修改</a>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="javascript:void();" onclick="deleteShopCart();" title="delete more">批量删除</a>
				<div id="sum">总计:<span>￥<%=sum %></span></div>
				<button id="gotoBalance" type="submit" name="action" value="balance">结算</button>
			</div>
			</form>
		</div>

	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
</body>
</html>
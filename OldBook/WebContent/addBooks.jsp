<%@ page language="java" import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% request.setCharacterEncoding("UTF-8") ;%>
<html>
  <head>
    <title>添加图书</title>
<style type="text/css">
#main{
width:1060px;
height:460px;
}
.input {  
    color: #003399; 
    font-family: "宋体"; 
    font-style: normal; 	
    border-color: #93BEE2 #93BEE2 #93BEE2 #93BEE2 ; 
    border: 1px #93BEE2 solid; 
    maxlength: 15;

.title{
	background-color:#B1C8F5;
}
.button {
    font-family: "Tahoma", "宋体";
    font-size: 9pt; color: #003399;
    border: 1px #003399 solid;
    color:#006699;
    border-bottom: #93bee2 1px solid; 
    border-left: #93bee2 1px solid; 
    border-right: #93bee2 1px solid; 
    border-top: #93bee2 1px solid;
    background-color: #e8f4ff;
    cursor: hand;
    font-style: normal ;
}
a {
  background-color: transparent;
  color:#000;  
  font-size: 18px;
  font-weight:bolder;  
  padding-left: 15px;
  text-decoration: none;
}

    
</style>
  </head>
  <body>
  <div id="main">
  <table cellSpacing=0 cellPadding=0 width=1024 align="center" bgColor=#ffffff background=pic/top1bg.jpg border=0>
				<tbody>
					<tr>
						<td width=1024>
							&nbsp;
						</td>
					</tr>
					</tbody>
			</table>
<table width="150" valign="center" align="center"><strong><font size="4" color="blue"><br>添加图书</font></strong></table>
  <table cellSpacing=0 cellPadding=0 width=1024 align="center" bgColor=#ffffff border=0>
 <center>

<form method="POST" action="AddBookServlet">
<table cellpadding="0" cellspacing="0" border="1"  align="center" width="1000px" style="margin:30px 0px 0px 0px;" >

	<tr>
		<td height="40" align="center">图书书名:</td>
		<td width="400" align="center"><input type="text" name="bookname" class="input"></td>
		<td height="40" align="center">作&nbsp;&nbsp;&nbsp;&nbsp;者:</td>
		<td width="400" align="center"><input type="text" name="author" class="input"></td>
	</tr>
	<tr>
		<td height="40" align="center">出&nbsp;版&nbsp;社:</td>
		<td width="400" align="center"><input type="text" name="press" class="input"></td>
		<td height="40" align="center">原书价格:</td>
		<td width="400" align="center"><input type="text" name="price" class="input"></td>
	</tr>	
	<tr>
		<td height="40" align="center">出版时间:</td>
		<td width="400" align="center"><p><input type="text" name="publicdate" class="input""></td>
		<td height="40" align="center">数&nbsp;&nbsp;&nbsp;&nbsp;量:</td>
		<td width="400" align="center"><p><input type="text" name="amount" class="input""></td>
	</tr>
	<tr>
		<td height="40" align="center">当前价格:</td>
		<td width="400" align="center"><p><input type="text" name="currprice" class="input"></td>
		<td  height="40" align="center">热度:</td>
		<td width="400" align="center"><input type="text" name="popularity" class="input"></td>
	</tr>
	<tr>
		<td height="40" align="center">图书介绍:</td>
		<td width="400" align="center"><p><textarea name="synopsis" cols="30" rows="5" class="input"></textarea></td>
	</tr>
</table>
<p></p>
<center>
<button id="addBook" type="submit" >添加</button>
<button id="reset" type="reset" >重置</button>
<button id="back" type="button" onclick="window.location='shop_management.jsp'">返回</button>
</center>
</form>
</center> 
<table cellSpacing=0 cellPadding=0 width=1024 align="center" bgColor=#ffffff background=pic/top1bg.jpg border=0>
				<tbody>
					<tr>
						<td width=1024>
							&nbsp;
						</td>
					</tr>
					</tbody>
			</table>
</div> 
  </body>
</html>

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<link
	href="${pageContext.request.contextPath }/bootstrap3/css/bootstrap.min.css"
	type="text/css" rel="stylesheet" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
</head>
<body>
	<div class="container-fluid" style="padding-left: 0px;">
	<% //Integer Count=Integer.parseInt(session.getAttribute("pbsum").toString()); 
	int bookCount=Integer.valueOf(request.getParameter("pbsum"));
	 for(int i=0;i<3;i++){ %>
		<div class="row">
		<% for(int j=0;j<4;j++){ 
		int c=i*4+j+1;
		String str="f"+String.valueOf(c);
		if(c<=bookCount){
		%>
			<div class="col-md-3 col-sm-3" >
				<a href="searching?pos=<%=c %>" class="thumbnail" > <img src="searching?type=img&&pos=<%=c %>"
					alt="..." ><span><%=new String(request.getParameter(str).getBytes("ISO-8859-1"), "UTF-8") %></span>
				</a>				
			</div>
		<%}} %>
		</div>
		<%} %>
		<div class="row">
			<div class="col-md-7 col-sm-7"></div>
			<div class="col-md-4 col-sm-4">
				<nav>
				<ul class="pagination">
					<li><a href="#" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a></li>
					<%int bookpage=Integer.valueOf(request.getParameter("psum"));//Integer.parseInt((String)session.getAttribute("psum"));
					if(bookpage!=0)
					{
					for(int i=0;i<bookpage;i++){ 
					if(i<5){%>
					<li><a href="searching?type=changePage&&pn=<%=i+1%>"><%=i+1 %></a></li>
					<% }else{%>
					<li><a href="searching?type=changePage&&pn=<%=i+1%>"><%=i+1 %></a></li>
						<%} %>
					<%}}
					else
					{%>
					<li><a href="#"> <span>没有找到相关内容！</span>
					</a></li>
					<%} %>
					<li><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
				</ul>
				</nav>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>

</body>
</html>
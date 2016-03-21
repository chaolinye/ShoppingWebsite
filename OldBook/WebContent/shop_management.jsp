<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>   
<%@page import="java.util.ArrayList"%> 
<%@page import="org.freedom.model.Book"%> 
<%@ page import="java.sql.ResultSet"%>
<%@ page import="org.freedom.service.SearchingService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
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
	<form method="GET" action="EditBookServlet">
		<div class="panel panel-default">
			<div class="panel-heading">书籍管理</div>
			
			<table class="table">
				<tr>
					<th>
						<input type="checkbox">全选
					</th>
					<th>
						图书名称
					</th>
					<th>
						单价
					</th>
					<th>
						数量
					</th>
					<th>
						出版社
					</th>
					<th>
						销售量
					</th>
					<th>
						操作
						
						
					</th>
				</tr>
				
  <%     
   	String sid = String.valueOf(session.getAttribute("shopSid"));
	
    SearchingService seaSer=new SearchingService();
    ArrayList<Book> booklist = seaSer.findBookByShop(sid);	

    
    int currPage = 1;
	int pageSize = 15;
	if (request.getParameter("page") != null) {
    	currPage = Integer.parseInt(request.getParameter("page"));
	}
    
	
	Book book;
    //int totalPage = 5;
	int totalRow  = booklist.size();
    //int totalRow = 4;
   	int totalPage = (totalRow % pageSize) == 0 ? totalRow / pageSize:(totalRow / pageSize) + 1;
   	
   	if (currPage == 0) {
    	currPage = 1;
   	}
   	
   	if (currPage > totalPage) {
    	currPage = totalPage;
   	}
   	
   	int start = (currPage - 1) * pageSize;
   	int end = currPage * pageSize ;
   	if (currPage == totalPage) {
    	end = totalRow;
   	}
    if(totalRow == 0) {
    	start = 1;
    	end = 0;
   	}
    
    System.out.print("asd");
    session.setAttribute("Shop_sid", sid);
    
	for (int i = start; i <end; i++) {
         
          book=booklist.get(i);
      
  %>
				<tr>
					<th>
						<input type="checkbox" name="checkbox" value=<%=book.getBid()%> onchange="changeval(<%=book.getBid()%>)">
					</th>
					<td>
					   <%=book.getBookName() %>
					</td>
					<td>
						<%=book.getPrice() %>
					</td>
					<td>
					    <%=book.getAmount() %>
					</td>
					<td>
						<%=book.getPress() %>
					</td>
					<td>
						<%=book.getPopularity()%>
					</td>
					

					<td>
					    <button id="deleteBook" type="button" onclick="deletebook(<%=book.getBid()%>,<%=book.getSid()%>)" >删除书籍</button>
					</td>
				</tr>
            <%
	        }
	       %>
		<div class="row">
		  <div class="col-md-7"></div>
			<div class="col-md-4">
				<nav>
				<ul class="pagination">
				<%	
		         if (currPage > 1) {
	   		         
	             %>
					<li><a href="shop_management.jsp?page=<%=currPage - 1%>" aria-label="Previous"> <span
							aria-hidden="true">&laquo;</span>
					</a>
					</li>
				  <%
		            }
	              %>
	              <%
	                  int k;
	                  for(k=0;k<totalPage;k++)
	                  {
	              %>
					<li><a href="shop_management.jsp?page=<%=k+1%>"><%=k+1%></a></li>
				 <%
		            }
	              %>
				<%
		       if (currPage < totalPage) {
	            %>	
					<li><a href="shop_management.jsp?page=<%=currPage + 1%>" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
			    <%
		         }
	            %>	
				</ul>
				</nav>
			</div>
			<div class="col-md-1"></div>
		</div>
			
			</table>
			
		</div>
      </form>
     <div class="panel-body">
				<button id="addBook" type="button" onclick="window.location='addBooks.jsp'">增加书籍</button>
				<button id="alterBook" type="button" onclick="window.location='alterBooks.jsp'">编辑书籍</button>
				<button id="submit_edit" type="button" onclick="window.location=reload()">完成</button>
				<button id="submit_edit" type="button" onclick="window.location='index.jsp'">返回</button>
	 </div>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
    
    <script type="text/javascript">
       function deletebook(bid,sid)
       {
    	   $.ajax({
				type:"POST",
				url:"deleteBookServlet",
				data:{"bookId":bid,"shopId":sid},
				dataType:"text",
				success:function(data){									
				}			
			});
       }
       
       function changeval(bid)
       {
    	   $.ajax({
				type:"POST",
				url:"AlterBookServlet",
				data:{"bookIdForAlter":bid},
				dataType:"text",
				success:function(data){									
				}			
			});
       }
    </script>
</body>
</html>
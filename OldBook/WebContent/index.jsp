<%@ page language="java" contentType="text/html; charset=utf-8" import="org.freedom.model.*"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>竹帛网</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/bootstrap3/css/bootstrap-theme.min.css">
<style type="text/css">  
        /*设置上一级菜单的样式*/  
        #menu  
        {  
            width:100%;  
            height:100%;  
            background-color:RGB(216,216,216);  
            padding:0px;  
            margin:0px;  
        }  
        /*去掉列表前的圆点*/  
        #menu ul  
        {  
            width:100%;  
            list-style-type:none;  
            padding:0px;  
            margin:0px; /*消除左侧间隙*/  
        }  
        #menu ul li  
        {  
            width:100%;/*填充满整个边栏*/  
            /* 
            margin:0px; 
            padding:0px; 
            */  
        }  
        #menu ul li a  
        {  
            display:block;  /*先转化成块级元素*/  
            color:Gray;  
            text-align:left;  
            text-decoration:none;  
            padding:10px 0px 10px 30px;/*设置距离左侧的边栏和上边距*/  
            font-size:14px;  
        }  
        #menu ul li a:hover  
        {  
            color:Orange;  
            text-align:left;  
            text-decoration:none;  
            padding:10px 0px 10px 30px;  
            font-size:14px;  
        }  
        /*设置子菜单的样式*/  
        #menu ul li ul  
        {  
            list-style-type:none;  
            /* 
            padding:0px; 
            margin:0px; 
            */  
        }  
        #menu ul li ul li  
        {  
            width:100%;    /*通过设置宽度来填充*/  
        }  
        #menu ul li ul li a  
        {  
            display:block;  
            /*此处可以通过设置来调整相关的样式*/  
            padding:6px 0px 6px 60px;  
            text-align:left;  
            text-decoration:none;  
            font-size:12px;  
        }  
        #menu ul li ul li a:hover  
        {  
            padding:6px 0px 6px 60px;  
            text-align:left;  
            text-decoration:none;  
            font-size:12px;  
            color:Black;  
            background-color:#CD2626;  
        }  
    </style>  
</head>
<body onload="loadFun()">
	<div class="container-fluid">
		<div class="row" style="margin-bottom: 20px">
			<div class="col-md-1">
				<a href="#">收藏该页</a>
			</div>
			<% if(session.getAttribute("user")==null&&session.getAttribute("shopUser")==null){ %>
			<div class="col-md-7"></div>
			<div class="col-md-2" align="center">Hello,欢迎来到竹帛网</div>
			<div class="col-md-1">
				<a href="login.jsp">请登录</a>&nbsp;&nbsp;<br> <a href="register.jsp">注册</a>
			</div>
			<div class="col-md-1">
				<a href="shoplogin.jsp">商家登录</a>&nbsp;&nbsp; <br>
				<a href="shopregister.jsp">商家注册</a>
			</div>
			<div class="col-md-1"></div>
			<div class="col-md-1"></div>
			
			<%}if(session.getAttribute("user")!=null){ %>
			<div class="col-md-5"></div>
			<div class="col-md-2" align="center">用户：<%=((User)session.getAttribute("user")).getRealName() %> </div>
			<div class="col-md-1"><a id="shopCart"  href="#" title="我的购物车">我的购物车</a></div>
			<div class="col-md-1" ><a href="order?type=open" title="我的订单">我的订单</a></div>
			<div class="col-md-2"><a>修改密码</a>&nbsp;&nbsp;<a href="exit" title="退出登录">退出</a></div>
			
			<%} if(session.getAttribute("shopUser")!=null){ %>
			<div class="col-md-5"></div>
			<div class="col-md-2" align="center">商家：<%=((Shop)session.getAttribute("shopUser")).getName() %> </div>
			<%  Shop shop=(Shop)session.getAttribute("shopUser");
			    session.setAttribute("shopSid", shop.getSid());
			    System.out.println(shop.getSid());%> 
			<div class="col-md-1"><a href="shop_management.jsp">书籍管理</a></div>
			<div class="col-md-2"><a>修改密码</a>&nbsp;&nbsp;
		   	<a href="exit" title="退出登录">退出</a></div>
			<%} %>
		</div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<div class="input-group">
					<div class="input-group-btn">
						<!-- Button and dropdown menu -->
						<button type="button" class="btn btn-default dropdown-toggle"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span id="bookOrShop1" >书名</span>&nbsp;<span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a id="bookOrShop2">作者</a></li>
						</ul> 
					</div>			
					<input type="text" class="form-control" id="searchcontent" name="searchcontent"   aria-label="...">
					<div class="input-group-btn">
						<button id="searchBtn" type="button" class="btn btn-danger" onclick="search()" >搜索</button>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row" style="margin-top:10px">
			<div class="col-md-3">
				<div id="menu">
				<ul>
					<li><a href="#" onclick="Show('IT')">IT</a>
						<ul id="IT">
							<li><a href="#" onclick="searchByCa('编程语言');">编程语言</a></li>
							<li><a href="#" onclick="searchByCa('应用开发');">应用开发</a></li>
							<li><a href="#"onclick="searchByCa('游戏开发');">游戏开发</a></li>
							<li><a href="#"onclick="searchByCa('计算机基础');">计算机基础</a></li>
							<li><a href="#"onclick="searchByCa('高级主题');">高级主题</a></li>
						</ul></li>
					<li><a href="#" onclick="Show('language')">外语</a>
						<ul id="language">
							<li><a href="#">English</a></li>
							<li><a href="#">Japanese</a></li>
							<li><a href="#">French</a></li>
							<li><a href="#">Other Language</a></li>
						</ul></li>
					<li><a href="#" onclick="Show('literature')">文学</a>
						<ul id="literature">
							<li><a href="#">传统文学</a></li>
							<li><a href="#">现代文学</a></li>
							<li><a href="#">国内文学</a></li>
							<li><a href="#">国外文学</a></li>
						</ul></li>
					<li><a href="#" onclick="Show('refenence')">教辅</a>
						<ul id="refenence">
							<li><a href="#">大中专教材教辅</a></li>
							<li><a href="#">中小学教材教辅</a></li>
							<li><a href="#">考试教材教辅</a>
						</ul></li>
					<li><a href="#" onclick="Show('charge')">经管</a>
						<ul id="charge">
							<li><a href="#">经济学</a></li>
							<li><a href="#">管理学 </a></li>
							<li><a href="#">会计学</a></li>
							<li><a href="#">投资谈判学 </a></li>
							<li><a href="#">领导学</a></li>
							<li><a href="#">营销学 </a></li>
						</ul></li>
					<li><a href="#" onclick="Show('medicine')">医学</a>
						<ul id="medicine">
							<li><a href="#">养生保健</a></li>
							<li><a href="#">预防治疗</a></li>
							<li><a href="#">心理健康</a></li>
							<li><a href="#">急救常识</a></li>
						</ul></li>
					<li><a href="#" onclick="Show('food')">美食</a>
						<ul id="food">
							<li><a href="#">饮食文化</a></li>
							<li><a href="#">各式菜谱</a></li>
							<li><a href="#">饮食杂志</a></li>
							<li><a href="#">药膳食疗</a></li>
						</ul></li>
					<li><a href="#" onclick="Show('society')">人文社科</a>
						<ul id="society">
							<li><a href="#">历史</a></li>
							<li><a href="#">哲学宗教</a></li>
							<li><a href="#">国学古籍</a></li>
							<li><a href="#">社会科学</a></li>
							<li><a href="#">文化</a></li>
							<li><a href="#">发来</a></li>
						</ul></li>
					<li><a href="#" onclick="Show('art')">艺术</a>
						<ul id="art">
							<li><a href="#">摄影</a></li>
							<li><a href="#">设计</a></li>
							<li><a href="#">绘画</a></li>
							<li><a href="#">音乐</a></li>
							<li><a href="#">舞蹈</a></li>
							<li><a href="#">漫画</a></li>
						</ul></li>
				</ul>
				</div>
			</div>
			<div class="col-md-9">
				<iframe frameborder="0" style="height:500px;width:100%;" src="index_body.jsp" name="body" id="iframe_body"></iframe>
			</div>
		</div>
	</div>


	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/jquery-1.11.2.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/bootstrap3/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		//实现店铺和书名的变换
		$('#bookOrShop2').click(function() {
			var bS2 = $('#bookOrShop2').text();
			if(bS2=="书名")
		{
			$.ajax({
				type:"GET",
				url:"searching?type=changeCa",
				data:{"By":"bookname"},
				dataType:"text",
				success:function(data){				
					$('#bookOrShop2').text($('#bookOrShop1').text());
					$('#bookOrShop1').text(bS2);
				}			
			});
		}
			else
				{
				$.ajax({
					type:"GET",
					url:"searching?type=changeCa",
					data:{"By":"author"},
					dataType:"text",
					success:function(data){				
						$('#bookOrShop2').text($('#bookOrShop1').text());
						$('#bookOrShop1').text(bS2);
					}			
				});
				}
		});
		/*网页加载时触发的函数*/
		function loadFun() {
			//获取<ul></ul>的所有子节点<li>节点5个  
			var array = document.getElementsByTagName("ul").item(1).childNodes;
			//遍历子节点  
			for (var i = 0; i < array.length; i++) {
				//获取<li></li>标签的子节点  
				var childnodes = array[i].childNodes;
				for (var j = 0; j < childnodes.length; j++) {
					//如果碰到主菜单中的<ul>标签就隐藏该子菜单的下拉菜单  
					if (childnodes[j].tagName == "UL") {
						childnodes[j].style.display = "none";
					}
				}
			}
		}
	    /*点击按钮是触发的事件*/  
        var arrays = new Array("IT","language","literature","refenence","charge","medicine","food","society","art");  
        function Show(tagId) {  
            for (var i = 0; i < arrays.length; i++) {  
                if (arrays[i] == tagId) {  
                    //设置全新的背景颜色  
                    document.getElementById(arrays[i]).parentNode.style.backgroundColor = "RGB(237,237,237)";  
                    document.getElementById(arrays[i]).style.display = "block";  
                }  
                else {  
                    //将背景颜色还原  
                    document.getElementById(arrays[i]).parentNode.style.backgroundColor = "RGB(216,216,216)";  
                    document.getElementById(arrays[i]).style.display = "none";  
                }  
            }  
        }  
        function search(){ 
        	$.ajax({
			type:"GET",
			url:"searching?type=aboutsearching",
			data:{"searchcontent":document.getElementById("searchcontent").value},
			dataType:"text",
			success:function(data){				
				if(document.getElementById("searchcontent").value!="")
				$('#iframe_body').attr("src",data);
			}			
		});
        }
        function searchByCa(str){ 
        	$.ajax({
			type:"GET",
			url:"searching?type=findByCa",
			data:{"CateName":str},
			dataType:"text",
			success:function(data){				
				$('#iframe_body').attr("src",data);
			}			
		});
        }
        function toShopCart(){
        	$('#iframe_body').attr("src","shopping_cart.jsp");
        	return false;
        }
        document.getElementById('shopCart').onclick=toShopCart;
	</script>
</body>
</html>
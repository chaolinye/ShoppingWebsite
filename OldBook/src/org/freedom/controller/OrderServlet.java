package org.freedom.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.freedom.model.Book;
import org.freedom.model.Category;
import org.freedom.model.User;
import org.freedom.service.CategoryService;
import org.freedom.service.OrderService;
import org.freedom.service.SearchingService;
/*
import org.freedom.model.User;
import org.freedom.service.UserService;
import org.freedom.util.StringUtil;

/**
 * 处理搜索部分
 * 以及搜索得到的信息在页面间的传递
 * @author Carver Wu
 *
 */
@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public OrderServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String content = null;
		request.setCharacterEncoding("utf-8");
		System.out.println("接到order请求");
		if(request.getParameter("type")!=null&&request.getParameter("type").equals("open"))
		{
		User user=(User) request.getSession().getAttribute("user");
		OrderService ser=new OrderService();		
		int num=ser.getOrderNum(user.getUid());
		if(num%3==0)num=num/3;
		else num=num/3+1;
		if(user!=null)
		{
			System.out.println("接到order请求!!!");
			String str="my_order.jsp"+"?ouid="+user.getUid()+"&&opage=0"+"&&orderpagesum="+num;
			System.out.println(str);
			response.sendRedirect(str);
		}
		else
		{			
			request.setAttribute("error", "登录超时！请重新登录");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		}
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("img"))
		{
			OutputStream output = response.getOutputStream();
			response.setContentType("image/png");
			ServletContext context = getServletContext();
			String imgpath=request.getParameter("bbpath");
			System.out.println("获取Order图片!!");
			InputStream imageIn;
			if(imgpath!=null)imageIn=context.getResourceAsStream(imgpath);
			else imageIn=context.getResourceAsStream("images/book5.png");
			BufferedInputStream bis=new BufferedInputStream(imageIn);
			BufferedOutputStream bos=new BufferedOutputStream(output);
			byte data[]=new byte[4096];
			int size=0; 
			size=bis.read(data); 
			 while (size!=-1)
			 {      
		            bos.write(data,0,size);           
		            size=bis.read(data);   
		     }   
		        bis.close();   
		        bos.flush(); 
		        bos.close(); 
		}
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("changepage"))
		{
			String ppage=request.getParameter("ppage");
			User user=(User) request.getSession().getAttribute("user");
			OrderService ser=new OrderService();		
			int num=ser.getOrderNum(user.getUid());
			if(num%3==0)num=num/3;
			else num=num/3+1;		
			String str="my_order.jsp"+"?ouid="+user.getUid()+"&&opage="+ppage+"&&orderpagesum="+num;
			System.out.println("换页"+str);
			response.sendRedirect(str);
		}
		
		return;			
	}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException {
	          this.doGet(request, response);
	      }
}

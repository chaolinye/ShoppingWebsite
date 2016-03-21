package org.freedom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.freedom.model.Shop;
import org.freedom.service.ShopService;
import org.freedom.util.StringUtil;

/**
 * Servlet implementation class ShopLoginServlet
 */
@WebServlet("/ShopLoginServlet")
public class ShopLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ShopService shopService=new ShopService();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String userName=request.getParameter("loginName");
		String password=request.getParameter("loginPassword");
		String checkCode=request.getParameter("checkCode");
		//把相关参数信息发在requestScope中，结合jsp实现填写的信息重写时不会消失的功能
		request.setAttribute("loginName", userName);
		request.setAttribute("loginPassword", password);
		request.setAttribute("checkCode", checkCode);
		//判断验证码是否正确
		if(!((String)request.getSession().getAttribute("checkCode")).equalsIgnoreCase(checkCode)){
			request.setAttribute("error","验证码错误");
			System.out.println("验证码错误");
			request.getRequestDispatcher("shoplogin.jsp").forward(request, response);
			return;
		}
		//判断账号密码是否为空
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
			System.out.println("用户名或密码不能为空！");
			request.setAttribute("error", "用户名或密码不能为空！");
			request.getRequestDispatcher("shoplogin.jsp").forward(request, response);
			return;
		}
		System.out.println("aasda");
		Shop shopuser=shopService.login(new Shop(userName,password));
		System.out.println("aasdasss");
		//判断用户是否存在
		if(shopuser==null){
			System.out.println("用户名或密码错误");
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("shoplogin.jsp").forward(request, response);
			return;
		}else{
			System.out.println("登录成功");
			request.getSession().setAttribute("shopUser", shopuser);
			response.sendRedirect("index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

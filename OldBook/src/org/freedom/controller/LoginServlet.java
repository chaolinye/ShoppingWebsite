package org.freedom.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.freedom.model.User;
import org.freedom.service.UserService;
import org.freedom.util.StringUtil;

/**
 * 登录控制器,处理登录请求
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	private UserService userService=new UserService();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String checkCode=request.getParameter("checkCode");
		//把相关参数信息发在requestScope中，结合jsp实现填写的信息重写时不会消失的功能
		request.setAttribute("userName", userName);
		request.setAttribute("password", password);
		request.setAttribute("checkCode", checkCode);
		//判断验证码是否正确
		if(!((String)request.getSession().getAttribute("checkCode")).equalsIgnoreCase(checkCode)){
			request.setAttribute("error","验证码错误");
			System.out.println("验证码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		//判断账号密码是否为空
		if(StringUtil.isEmpty(userName)||StringUtil.isEmpty(password)){
			System.out.println("用户名或密码不能为空！");
			request.setAttribute("error", "用户名或密码不能为空！");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}
		
		User user=userService.login(new User(userName,password));
		//判断用户是否存在
		if(user==null){
			System.out.println("用户名或密码错误");
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}else{
			System.out.println("登录成功");
			request.getSession().setAttribute("user", user);
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

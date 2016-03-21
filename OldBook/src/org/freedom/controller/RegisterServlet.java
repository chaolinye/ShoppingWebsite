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
 * 处理注册请求
 * @author chaolin
 *
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		
		if (request.getParameter("type")!=null&&request.getParameter("type").equals("checkUserName")) {
			//处理用户名是否存在的请求
			String userName = request.getParameter("userName");
			//判断用户名是否为空
			if (StringUtil.isEmpty(userName)) {
				System.out.println("用户名不能为空");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().println("用户名不能为空");
			} else {
				//判断用户名是否已经存在
				if (userService.isUserExisting(new User(userName))) {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().println("该用户已存在");
				} else {
					response.setContentType("text/html;charset=utf-8");
					response.getWriter().println("");
				}
			}
		
			return;
		} else {
			//处理注册请求
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String realName = request.getParameter("realName");
			System.out.println(realName);
			String identity = request.getParameter("identity");
			String checkCode=request.getParameter("checkCode");
			//把相关参数信息发在requestScope中，结合jsp实现填写的信息重写时不会消失的功能
			request.setAttribute("userName", userName);
			request.setAttribute("password", password);
			request.setAttribute("email", email);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("realName", realName);
			request.setAttribute("identity", identity);
			request.setAttribute("checkCode", checkCode);
			//判断参数信息是否完整
			if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(password) || StringUtil.isEmpty(email)
					|| StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(realName)
					|| StringUtil.isEmpty(identity)||StringUtil.isEmpty(checkCode)) {
				request.setAttribute("error", "信息填写不完整");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
				return;
			} else {
				//判断验证码是否正确
				if(!((String)request.getSession().getAttribute("checkCode")).equalsIgnoreCase(checkCode)){
					request.setAttribute("error","验证码错误");
					System.out.println("验证码错误");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
					return;
				}
				//注册用户
				User user = userService.register(new User(userName, password, email, phoneNumber, realName, identity));
				//判断注册是否成功
				if(user==null){
					request.setAttribute("error", "信息填写有误");
					request.getRequestDispatcher("/register.jsp").forward(request, response);
					return;
				}else{
					System.out.println("注册成功");
					request.getSession().setAttribute("user", user);
					response.sendRedirect("index.jsp");
				}
				
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

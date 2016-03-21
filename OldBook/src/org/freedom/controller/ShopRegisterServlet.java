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
 * Servlet implementation class ShopRegisterServlet
 */
@WebServlet("/ShopRegisterServlet")
public class ShopRegisterServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ShopService shopService=new ShopService(); 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShopRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		if (request.getParameter("type")!=null&&request.getParameter("type").equals("checkloginName")) {
			//处理用户名是否存在的请求
			String userName = request.getParameter("loginName");
			//判断用户名是否为空
			if (StringUtil.isEmpty(userName)) {
				System.out.println("用户名不能为空");
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().println("用户名不能为空");
			} else {
				//判断用户名是否已经存在
				if (shopService.isShopExisting(new Shop(userName))) {
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
			String loginName = request.getParameter("loginName");
			System.out.println(loginName);
			String loginPassword = request.getParameter("loginPassword");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String shopName = request.getParameter("shopName");
			String realName = request.getParameter("realName");
			String address  = request.getParameter("address");
			System.out.println(realName);
			String identity = request.getParameter("identity");
			String checkCode=request.getParameter("checkCode");
			//把相关参数信息发在requestScope中，结合jsp实现填写的信息重写时不会消失的功能
			request.setAttribute("loginName",loginName);
			request.setAttribute("loginpassword", loginPassword);
			request.setAttribute("email", email);
			request.setAttribute("phoneNumber", phoneNumber);
			request.setAttribute("realName", realName);
			request.setAttribute("address", address);
			request.setAttribute("identity", identity);
			request.setAttribute("shopName", shopName);
			request.setAttribute("checkCode", checkCode);
			//判断参数信息是否完整
			if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(loginPassword) || StringUtil.isEmpty(email)
					|| StringUtil.isEmpty(phoneNumber) || StringUtil.isEmpty(realName)
					|| StringUtil.isEmpty(identity)||StringUtil.isEmpty(checkCode)) {
				request.setAttribute("error", "message is not enough!");
				request.getRequestDispatcher("/shopregister.jsp").forward(request, response);
				return;
			} else {
				//判断验证码是否正确
				if(!((String)request.getSession().getAttribute("checkCode")).equalsIgnoreCase(checkCode)){
					request.setAttribute("error","验证码错误");
					System.out.println("验证码错误");
					request.getRequestDispatcher("/shopregister.jsp").forward(request, response);
					return;
				}
				//注册用户
				Shop shop = shopService.register(new Shop(loginName,loginPassword,
						shopName,realName,identity,email,
						address,phoneNumber));
				//判断注册是否成功
				if(shop==null){
					request.setAttribute("error", "message error!");
					request.getRequestDispatcher("/shopregister.jsp").forward(request, response);
					return;
				}else{
					System.out.println("注册成功");
					request.getSession().setAttribute("shopUser", shop);
					response.sendRedirect("index.jsp");
				}
				
			}
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

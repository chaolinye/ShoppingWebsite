package org.freedom.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 处理退出请求
 */
@WebServlet("/exit")
public class ExitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//调用此方法可以通过记录显示
		// TODO Auto-generated method stub
		request.getSession().removeAttribute("user");//删除属性
		request.getSession().removeAttribute("shopUser");
		response.sendRedirect("index.jsp");//重定位网页到index
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//调用此方法可以不被别人查到表单并且无字数限制
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package org.freedom.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.freedom.util.DatabaseConn;

/**
 * Servlet implementation class AlterBookServlet
 */
@WebServlet("/AlterBookServlet")
public class AlterBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session; 
	private static String bookId;
	
	public String handleString(String s){
		try{
			byte bb[]=s.getBytes("utf-8");
			s=new String(bb);
		}
		catch(Exception ee){
		}
		return s;
		}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlterBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		
	DatabaseConn conn = new DatabaseConn();
		
        session = request.getSession();
		
		int price=Integer.parseInt(request.getParameter("price").trim());
		int amount=Integer.parseInt(request.getParameter("amount").trim());
		int popularity=Integer.parseInt(request.getParameter("popularity").trim());
		int currprice=Integer.parseInt(request.getParameter("currprice").trim());
		String bookname=handleString(request.getParameter("bookname"));
		String author=handleString(request.getParameter("author"));
		String press=handleString(request.getParameter("press"));
		String publicaDate=handleString(request.getParameter("publicdate"));
		String synopsis=handleString(request.getParameter("synopsis"));
        
		String sid = String.valueOf(session.getAttribute("Shop_sid")); 
       
        System.out.println(sid);
        //String[] str = request.getParameterValues("checkbox");
        
        //if(str==null) System.out.println("qwewq");
       
		String sql = "UPDATE t_book SET bookName = '"+bookname+"',author = '"+author+"',press='"+press+"',publicationDate ='"+publicaDate+"',synopsis ='"+synopsis+"',amount ="+amount+",price ="+price+",currPrice ="+currprice+" WHERE sid = '"+sid+"' AND bid ='"+bookId+"'";
		int i = 0;
		i = conn.executeUpdate(sql);
		System.out.println(sql);
		conn.close();
		if(i !=0){
		RequestDispatcher dispatcher=
				request.getRequestDispatcher("alterBooksSuccess.jsp");//ת�������ȷҳ��
		dispatcher.forward(request,response);
		}
		else{
			RequestDispatcher dispatcher=
				request.getRequestDispatcher("alterBooksFailed.jsp");//ת����Ӵ���ҳ��
		dispatcher.forward(request,response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
        bookId = String.valueOf(request.getParameter("bookIdForAlter"));
		
		System.out.println(bookId);
		
		if(bookId==null)
		{
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("alterBooksFailed2.jsp");//ת�������ȷҳ��
			dispatcher.forward(request,response);
		}
	
	
	}

}

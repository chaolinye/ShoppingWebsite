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
import org.freedom.model.Book;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session;   
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
    public AddBookServlet() {
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
		Book book=new Book();
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
		int i = 0;
		String sql = "INSERT INTO t_book(bookName,author,press,publicationDate,popularity,synopsis,sid,amount,price,currPrice)"
				+ " VALUES('" +bookname + "','" + author+ "','" + press+ "','" +publicaDate+ "','" +popularity+ "','" +synopsis+ "','"+sid+"','" +amount+"','"+price+ "','" +currprice + "')";
		i = conn.executeUpdate(sql);
		System.out.println(sql);
		conn.close();
		if(i !=0){
		RequestDispatcher dispatcher=
				request.getRequestDispatcher("addBooksSuccess.jsp");//ת�������ȷҳ��
		dispatcher.forward(request,response);
		}
		else{
			RequestDispatcher dispatcher=
				request.getRequestDispatcher("addBooksFailed.jsp");//ת����Ӵ���ҳ��
		dispatcher.forward(request,response);
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

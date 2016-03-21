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

import java.util.ArrayList;

/**
 * Servlet implementation class EditBookServlet
 */
@WebServlet("/EditBookServlet")
public class EditBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Book> booklist;
	private helpControlBooks helperConBook = new helpControlBooks();
	private static final int DATA_PER_PAGE = 15;
	private HttpSession session;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditBookServlet() {
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
		
		String sid =String.valueOf(session.getAttribute("Shop_sid")); 
		
		//= String.valueOf(request.getParameter("sid"));
		int page = 0;
	    try {
			 
	    	  booklist = helperConBook.getAllBooks(sid);	
			 		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.close();
		
		page = getTotalPage( booklist.size());	
		session.setAttribute("bookList", booklist);  
	    session.setAttribute("BookAmount", booklist.size());
	    session.setAttribute("totalPage", page);
		request.getRequestDispatcher("shop_management.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		//deleteBook(request,response);
	
	}
	public int getTotalPage(int booktotalAmount)
	{
		int page = 0;
		page = (int)Math.ceil((booktotalAmount + 1.0 - 1.0) / DATA_PER_PAGE);
		return page;
	}
	public void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		Book book1 = null;
		/*String[] str=request.getParameterValues("checkbox");
	    
		for(int i=0;i<15;i++)    
		 System.out.println(str[i]);
	    */
		
	    int i=0;
	    String sid =String.valueOf(request.getParameter("shopId")); 
	    String bid =String.valueOf(request.getParameter("bookId")); 
	    boolean flag =false;
	    
	    flag=helperConBook.deleteBook(sid,bid);
	    	      	

		if(flag==true){
			RequestDispatcher dispatcher=
					request.getRequestDispatcher("deleteBooksSuccess.jsp");//ת�������ȷҳ��
			dispatcher.forward(request,response);
			}
			else{
				RequestDispatcher dispatcher=
					request.getRequestDispatcher("deleteBooksFailed.jsp");//ת����Ӵ���ҳ��
			dispatcher.forward(request,response);
			} 
	      
	}
}

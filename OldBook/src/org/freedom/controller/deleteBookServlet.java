package org.freedom.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.freedom.model.Book;

/**
 * Servlet implementation class deleteBookServlet
 */
@WebServlet("/deleteBookServlet")
public class deleteBookServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArrayList<Book> booklist;
	private helpControlBooks helperConBook = new helpControlBooks();
	private HttpSession session;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		/*Book book1 = null;
		String[] str=request.getParameterValues("checkbox");
	    System.out.println(str);
	    
	    int i=0;
	    String sid =String.valueOf(session.getAttribute("Shop_sid")); 
	    boolean flag =false;
	    for(;i<booklist.size();i++)
	    {
	    	 if(str[i]!=null)
	    	 { book1=booklist.get(i);
	    	   flag=helperConBook.deleteBook(sid,String.valueOf(book1.getBid()));
	    	  
	    	 }
	    	 if(flag==false) break;
	    }
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
	      */
		    String sid =String.valueOf(request.getParameter("shopId")); 
		    String bid =String.valueOf(request.getParameter("bookId")); 
		    boolean flag =false;
		    System.out.println(sid);
		    System.out.println(bid);
		    
		    flag=helperConBook.deleteBook(sid,bid);
		    	      	
            if(!flag)  System.out.println("xxxxxz");
			if(flag)System.out.println("xxxxxz111");
            
			if(flag==true){
				RequestDispatcher dispatcher=
						request.getRequestDispatcher("deleteBookSuccess.jsp");//ת�������ȷҳ��
				dispatcher.forward(request,response);
				}
				else{
					RequestDispatcher dispatcher=
						request.getRequestDispatcher("deleteBookFailed.jsp");//ת����Ӵ���ҳ��
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

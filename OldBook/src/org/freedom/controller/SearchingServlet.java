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
import org.freedom.service.CategoryService;
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
@WebServlet("/searching")
public class SearchingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public SearchingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		String content = null;
		request.setCharacterEncoding("utf-8");
		System.out.println("借刀请求");
		if(request.getParameter("type")!=null&&request.getParameter("type").equals("aboutsearching"))
		{//处理来自index的搜索要求
			System.out.println("借到搜索请求");
	    content = request.getParameter("searchcontent");
	    request.getSession().setAttribute("searchContent", new String(content));
	    request.getSession().setAttribute("searchingPage", new String("1"));
	    request.getSession().setAttribute("psum", new String("1"));
	    request.getSession().setAttribute("pbsum", new String("1"));
	    request.getSession().setAttribute("bsum", new String("0"));
	    Book lastbook=(Book) request.getSession().getAttribute("book1");
	    int l=1;
	    String lastb="book1";	   
	    while(lastbook!=null)
	    {
	    	request.getSession().removeAttribute(lastb);
	    	l++;
	    	lastb="book"+String.valueOf(l);	
	    	lastbook=(Book) request.getSession().getAttribute(lastb);
	    }
	    ///*
	    SearchingService ser=new SearchingService();
	    Book book=null;
	    if(request.getSession().getAttribute("searchingCa")!=null)
	    {
	    if(request.getSession().getAttribute("searchingCa").equals("author"))
	    {
	    	book=ser.findbookByAuthor(content);
	    }
	    else
	    {
	    	book=ser.findbook(content);
	    }
	    }
	    else
	    {
	    	book=ser.findbook(content);
	    }
	    if(book!=null)
	    {//如果查到书了
	    	System.out.println("找到了书");
	    request.getSession().setAttribute("book1", book);
	    int j=2;
	    while(true)
	    {//搜索到所有的书，放入会话中
	    	String bookn="book"+String.valueOf(j);
	    	Book bookl=ser.findNextBook();
	    	if(bookl==null)break;
	    	request.getSession().setAttribute(bookn, bookl);
	    	j++;
	    }
	    int bookcount=ser.getbooksum();
	    int pbookcount=0,pagecount=0,temp=bookcount;
	    if(bookcount%12!=0)pagecount=bookcount/12+1;
	    else pagecount=bookcount/12;
	    if(pagecount==0)pagecount=1;
	    if(bookcount>=12)pbookcount=12;
	    else pbookcount=bookcount;
	    System.out.println("bookcount:"+bookcount+"  pagecount:"+pagecount+"  pbookcount:"+pbookcount);
	    request.getSession().setAttribute("bsum", String.valueOf(bookcount));
	    request.getSession().setAttribute("pbsum", String.valueOf(pbookcount));
	    request.getSession().setAttribute("psum", String.valueOf(pagecount));
	    ser.closeService();
	    response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("book_list.jsp");
		response.getWriter().println("?pbsum=");
		response.getWriter().println(String.valueOf(pbookcount));
		response.getWriter().println("&&psum=");
		response.getWriter().println(String.valueOf(pagecount));
		response.getWriter().println("&&f1=");
		int b= Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
		b=(b-1)*12+1;
		String sttr="book"+String.valueOf(b);
		Book books=(Book) request.getSession().getAttribute(sttr);
		response.getWriter().println(books.bookS());
		for(int i=2;i<13;i++)
		{
			int a= Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
			a=(a-1)*12+i;
			String bookn="book"+String.valueOf(a);
			Book booksn=(Book) request.getSession().getAttribute(bookn);
			if(booksn!=null)
			{
			String fn="&&f"+String.valueOf(i)+"="+booksn.bookS();
			response.getWriter().println(fn);
			}
			else 
			{
				String fn="&&f"+String.valueOf(i)+"="+" ";
				response.getWriter().println(fn);
			}
		}
	    }
	    else
	    {
	    	response.getWriter().println("book_list.jsp?psum=0&&pbsum=0");
	    	ser.closeService();
		}
		}
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("img"))
		{
			OutputStream output = response.getOutputStream();
			response.setContentType("image/png");
			ServletContext context = getServletContext();
			int bpos=Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
			bpos=(bpos-1)*12+Integer.parseInt(request.getParameter("pos"));
			String bookPos="book"+String.valueOf(bpos);
			System.out.println("获取图片位置"+bookPos);
			Book books=(Book) request.getSession().getAttribute(bookPos);
			InputStream imageIn;
			if(books!=null)
			{
			imageIn=context.getResourceAsStream(books.getImage());
			}
			else
			{
			imageIn=context.getResourceAsStream("images/book5.png");
			}
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
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("changePage"))
		{
			request.getSession().setAttribute("searchingPage",request.getParameter("pn"));
			response.setContentType("text/html;charset=utf-8");
			content="book_list.jsp";
			content=content+"?f1=";
			int b= Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
			int bookcount=Integer.parseInt((String)request.getSession().getAttribute("bsum"));
		    int pbookcount=0,pagecount=Integer.parseInt((String)request.getSession().getAttribute("psum"));
		    if(bookcount>=b*12)pbookcount=12;
		    else pbookcount=bookcount-(b-1)*12;
			b=(b-1)*12+1;
			String sttr="book"+String.valueOf(b);
			Book books=(Book) request.getSession().getAttribute(sttr);
			if(books!=null)content=content+books.bookS()+"&&psum="+pagecount+"&&pbsum="+pbookcount;
			for(int i=2;i<13;i++)
			{
				int a= Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
				a=(a-1)*12+i;
				String bookn="book"+String.valueOf(a);
				Book booksn=(Book) request.getSession().getAttribute(bookn);
				if(booksn!=null)
				{
				String fn="&&f"+String.valueOf(i)+"="+booksn.bookS();
				content=content+fn;
				}
				else 
				{
					String fn="&&f"+String.valueOf(i)+"="+" ";
					content=content+fn;
				}
			}
			response.sendRedirect(content);
		}
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("changeCa"))
		{
			System.out.println("更改目录请求");
			response.setContentType("text/html;charset=utf-8");	
			request.getSession().setAttribute("searchingCa", request.getParameter("By"));
			System.out.println("更改目录成功！");
			response.getWriter().println("succ");
		}
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("findByCa"))
		{
			String CateName=request.getParameter("CateName");
			request.getSession().setAttribute("searchingPage", new String("1"));
		    request.getSession().setAttribute("psum", new String("1"));
		    request.getSession().setAttribute("pbsum", new String("1"));
		    request.getSession().setAttribute("bsum", new String("0"));
		    Book lastbook=(Book) request.getSession().getAttribute("book1");
		    int l=1;
		    String lastb="book1";	   
		    while(lastbook!=null)
		    {
		    	request.getSession().removeAttribute(lastb);
		    	l++;
		    	lastb="book"+String.valueOf(l);	
		    	lastbook=(Book) request.getSession().getAttribute(lastb);
		    }
		    CategoryService ser=new CategoryService();
		    Category cate=null;
		    cate=ser.findCaByName(CateName);
		    Book book=null;		    
		    SearchingService ser1=new SearchingService();
		    if(cate!=null) book=ser1.findbookByCa(cate.getCid());
		    if(book!=null)
		    {//如果查到书了
		    	System.out.println("找到了书");
		    request.getSession().setAttribute("book1", book);
		    int j=2;
		    while(true)
		    {//搜索到所有的书，放入会话中
		    	String bookn="book"+String.valueOf(j);
		    	Book bookl=ser1.findNextBook();
		    	if(bookl==null)break;
		    	request.getSession().setAttribute(bookn, bookl);
		    	j++;
		    }
		    int bookcount=ser1.getbooksum();
		    int pbookcount=0,pagecount=0,temp=bookcount;
		    if(bookcount%12!=0)pagecount=bookcount/12+1;
		    else pagecount=bookcount/12;
		    if(pagecount==0)pagecount=1;
		    if(bookcount>=12)pbookcount=12;
		    else pbookcount=bookcount;
		    System.out.println("bookcount:"+bookcount+"  pagecount:"+pagecount+"  pbookcount:"+pbookcount);
		    request.getSession().setAttribute("bsum", String.valueOf(bookcount));
		    request.getSession().setAttribute("pbsum", String.valueOf(pbookcount));
		    request.getSession().setAttribute("psum", String.valueOf(pagecount));
		    ser.closeService();
		    ser1.closeService();
		    response.setContentType("text/html;charset=utf-8");
			response.getWriter().println("book_list.jsp");
			response.getWriter().println("?pbsum=");
			response.getWriter().println(String.valueOf(pbookcount));
			response.getWriter().println("&&psum=");
			response.getWriter().println(String.valueOf(pagecount));
			response.getWriter().println("&&f1=");
			int b= Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
			b=(b-1)*12+1;
			String sttr="book"+String.valueOf(b);
			Book books=(Book) request.getSession().getAttribute(sttr);
			response.getWriter().println(books.bookS());
			for(int i=2;i<13;i++)
			{
				int a= Integer.parseInt((String) request.getSession().getAttribute("searchingPage"));
				a=(a-1)*12+i;
				String bookn="book"+String.valueOf(a);
				Book booksn=(Book) request.getSession().getAttribute(bookn);
				if(booksn!=null)
				{
				String fn="&&f"+String.valueOf(i)+"="+booksn.bookS();
				response.getWriter().println(fn);
				}
				else 
				{
					String fn="&&f"+String.valueOf(i)+"="+" ";
					response.getWriter().println(fn);
				}
			}
		    }
		    else
		    {
		    	ser.closeService();
		    	ser1.closeService();
		    	response.getWriter().println("book_list.jsp?psum=0&&pbsum=0");
			}
		}
		else if(request.getParameter("type")!=null&&request.getParameter("type").equals("Mimg"))
		{
			System.out.println("获取详细图片请求");
			OutputStream output = response.getOutputStream();
			response.setContentType("image/png");
			ServletContext context = getServletContext();
			String bookPos=request.getParameter("book");
			if(bookPos!=null)
			System.out.println("获取详细图片位置"+bookPos);
			Book books=(Book) request.getSession().getAttribute(bookPos);
			InputStream imageIn;
			if(books!=null)
			{
			imageIn=context.getResourceAsStream(books.getImage());
			}
			else
			{
			imageIn=context.getResourceAsStream("images/book5.png");
			}
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
		else
		{//进入书籍详细页面
			System.out.println("其他请求");
			content="book_msg.jsp";
			response.setContentType("text/html;charset=utf-8");	
			int p=Integer.valueOf(request.getParameter("pos"));
			p=p+12*(Integer.valueOf((String) request.getSession().getAttribute("searchingPage")))-12;
			String bookPos="book"+String.valueOf(p);
			Book books=(Book) request.getSession().getAttribute(bookPos);
			if(books!=null)
				{
				request.getSession().setAttribute("Xbid", books.getBid());
				request.getSession().setAttribute("Xname", books.getBookName());
				request.getSession().setAttribute("Xcredit", String.valueOf(books.getbookShopCredit()));
				request.getSession().setAttribute("Xpressd", books.getPublicationDate());
				request.getSession().setAttribute("Xamount", books.getAmount());
				request.getSession().setAttribute("Xsy", books.getSynopsis());
				request.getSession().setAttribute("Xauthor", books.getAuther());
				request.getSession().setAttribute("Xpress", books.getPress());
				request.getSession().setAttribute("Xshop", books.getbookShop());
				request.getSession().setAttribute("Xprice", String.valueOf(books.getPrice()));
				request.getSession().setAttribute("Xhot", String.valueOf(books.getPopularity()));
				content=content+"?book="+bookPos;
				response.sendRedirect(content);
				}
			//response.sendRedirect("book_list.jsp");
			response.getWriter().println("空书架");
		}
		    return;			
	}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	              throws ServletException, IOException {
	          this.doGet(request, response);
	      }
}

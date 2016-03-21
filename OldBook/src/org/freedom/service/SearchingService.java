package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.freedom.model.Book;
import org.freedom.model.*;
import org.freedom.util.DbUtil;

/**
 * 关于书籍搜索的service层，主要写搜索时的相关方法
 * @author Carver Wu
 *
 */
public class SearchingService {
	private DbUtil dbUtil = new DbUtil();
	private Connection con;
	private ResultSet rs;
	private int booksum=0;
	
	public ArrayList<Book> findBookByShop(String sid)
	{
		String sql = "select * from t_book where  sid=?";
		ArrayList<Book> booklist = new ArrayList<Book>();
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new String(sid.getBytes("ISO-88"
					+ "59-1"), "UTF-8"));
			this.rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
			    
				booklist.add(book);
			} 
				
			
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {}
		
		return booklist;
	}
	
	
	public Book findbook(String name)
	{
		String sql = "select * from t_book where bookName like ?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+new String(name.getBytes("ISO-8859-1"), "UTF-8")+"%");
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Book findbookByCa(int name)
	{
		String sql = "select * from t_book where cid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, name);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Book findbookByAuthor(String name)
	{
		String sql = "select * from t_book where author like?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			//System.out.println("搜索作者名称："+new String(name.getBytes("ISO-8859-1"), "UTF-8"));
			pstmt.setString(1, "%"+new String(name.getBytes("ISO-8859-1"), "UTF-8")+"%");
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Book findbookYouMayLike(int name)
	{
		int cid=0;
		OrderService oser=new OrderService();
		OrderItemService iser=new OrderItemService();
		Order order=oser.findUserLastOrder(name);
		if(order==null)return null;
		OrderItem item=iser.findItemByOrder(order.getOid());
		cid=item.getBid();
		cid=this.findbookById(cid).getCid();
		iser.closeService();
		oser.closeService();
		String sql = "select * from t_book where cid=?";//在这SQL后面加人气属性的降序具体怎么加我忘了
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Book findbookPopular()
	{
		String sql = "select * from t_book";//在这SQL后面加人气属性的降序具体怎么加我忘了
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Book findLatestbook()
	{
		String sql = "select * from t_book";//在这后面加SQL通过id降序排列，具体怎么写我忘了
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Book findbookById(int id)
	{
		String sql = "select * from t_book where bid =?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
				rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
				rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
				rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
				booksum++;
				return book;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询book出错：" + e.getMessage());
			return null;
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public Book findNextBook()
	{
		try{
		if (rs.next()) 
		{
			Book book = new Book(rs.getInt("bid"),rs.getString("bookName"),
			rs.getString("author"),rs.getString("press"),rs.getString("publicationDate"),
			rs.getInt("popularity"),rs.getString("synopsis"),rs.getInt("sid"),rs.getInt("amount"),
			rs.getInt("cid"),rs.getFloat("price"),rs.getFloat("currPrice"),rs.getString("image"));
			booksum++;
			return book;
		} 
		else
		{
			return null;
		}
		}catch (Exception e) {
			System.out.println("查询nextbook出错：" + e.getMessage());
			return null;
		}
	}
	public int getbooksum()
	{
		return booksum;
	}
	public void closeService()
	{
		try {
			dbUtil.closeCon(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


package org.freedom.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.freedom.model.Book;
import org.freedom.util.DatabaseConn;

public class helpControlBooks {
	private DatabaseConn conn = new DatabaseConn();
	
	/**
	 * ��ѯ����ͼ��
	 * 
	 * 
	 */
	public ArrayList<Book> getAllBooks(String sid){
		Book books = null;
		books = new Book();
		ArrayList<Book> booklist = null;
		String sql = "SELECT * FROM t_book WHERE sid = " +sid;
		ResultSet rs = conn.executeQuery(sql);
		try {
			
			while(rs != null && rs.next()) {	
				books.setBid(rs.getInt(1));
				books.setBookName(rs.getString(2));
				books.setAuthor(rs.getString(3));
				books.setPress(rs.getString(4));
				books.setPublicationDate(rs.getString(5));
				books.setPopularity(rs.getInt(6));
				books.setSynopsis(rs.getString(7));
				books.setSid(rs.getInt(8));
				books.setAmount(rs.getInt(9));
				books.setCid(rs.getInt(10));
				books.setPrice(rs.getInt(11));
				books.setCurrPrice(rs.getInt(12));
				books.setImage(rs.getString(13));
			    
				booklist.add(books);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn.close();
		return booklist;
	}
	/**
	 * ����ͼ��
	 * @param sid
	 */
	public boolean addBook(String bookna,String auth,String pres,String publicDate,String syn,String si,String amou,String pri,String curPri){
		String sql = "INSERT INTO t_book(bookName,author,press,publicationDate,synopsis,sid,amount,price,currPrice)";
		String values = "VALUES('"+bookna+"','"+auth+"','"+pres+"','"+publicDate+"','"+syn+"',"+si+","+amou+","+pri+","+curPri+")";
		sql = sql+values;
		int i=0;
		i=conn.executeUpdate(sql);
		conn.close();
        if(i!=0) return true;
        else return false;
	}	
	
	/**
	 * ɾ��ͼ��
	 * @param sid,bid
	 */
	
	public boolean deleteBook(String sid,String bid){
		String sql = "DELETE FROM t_book WHERE sid = " + sid + " AND bid ="+bid;
		int i=0;
		i=conn.executeUpdate(sql);
		conn.close();
		if(i!=0) return true;
        else return false;
	}	
	

	/**
	 * �޸�ͼ����Ϣ
	 * @param id
	 * @param s
	 * @return
	 */
	public boolean updateBook(String bid,String bookna,String auth,String pres,String publicDate,String syn,String sid,String amou,String pri,String curPri){
		String sql="UPDATE t_book SET bookName = '"+bookna+"',author = '"+auth+"',press='"+pres+"',publicationDate ='"+publicDate+"',synopsis ='"+syn+"',amount ="+amou+",price ="+pri+",currPrice ="+curPri+" WHERE sid = " +sid+ "AND bid ="+bid;
		int i=0;
		i=conn.executeUpdate(sql);
		conn.close();		
		if(i!=0) return true;
        else return false;
	}
	

	
	
}


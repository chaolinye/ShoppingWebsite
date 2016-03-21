package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.freedom.model.Book;
import org.freedom.model.Category;
import org.freedom.util.DbUtil;

/**
 *关于目录的service层，主要写搜索时的相关方法
 * @author Carver Wu
 *
 */
public class CategoryService {
	private DbUtil dbUtil = new DbUtil();
	private Connection con;
	private ResultSet rs;

	public Category findCaByName(String name)
	{
		String sql = "select * from t_category where name=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new String(name.getBytes("ISO-8859-1"), "UTF-8"));
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Category cate=new Category(rs.getInt("cid"),rs.getString("name"),rs.getString("synopsis"));
				return cate;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Category出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Category findCaById(int cid)
	{
		String sql = "select * from t_category where cid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Category cate=new Category(rs.getInt("cid"),rs.getString("name"),rs.getString("synopsis"));
				return cate;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Category出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Category findNextCategory()
	{
		try{
		if (rs.next()) 
		{
			Category cate = new Category(rs.getInt("cid"),rs.getString("name"),rs.getString("synopsis"));
			return cate;
		} 
		else
		{
			return null;
		}
		}catch (Exception e) {
			System.out.println("查询nextCategory出错：" + e.getMessage());
			return null;
		}
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


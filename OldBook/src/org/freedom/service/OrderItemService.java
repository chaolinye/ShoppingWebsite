package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.freedom.model.Book;
import org.freedom.model.Category;
import org.freedom.model.OrderItem;
import org.freedom.util.DbUtil;

/**
 *关于订单物品的service层，主要写搜索时的相关方法
 * @author Carver Wu
 *
 */
public class OrderItemService {
	private DbUtil dbUtil = new DbUtil();
	private Connection con;
	private ResultSet rs;

	public OrderItem findItemById(int id)
	{
		String sql = "select * from t_orderitem where orderitemid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				OrderItem item=new OrderItem(rs.getInt("orderItemId"),rs.getInt("amount"),rs.getInt("bid"),rs.getInt("oid"));
				return item;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Item出错：" + e.getMessage());
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
	
	public OrderItem findItemByOrder(int id)
	{
		String sql = "select * from t_orderitem where oid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				OrderItem item=new OrderItem(rs.getInt("orderItemId"),rs.getInt("amount"),rs.getInt("bid"),rs.getInt("oid"));
				return item;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Item出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public OrderItem findNextItem()
	{
		try{
		if (rs.next()) 
		{
			OrderItem item=new OrderItem(rs.getInt("orderItemId"),rs.getInt("amount"),rs.getInt("bid"),rs.getInt("oid"));
			return item;
		} 
		else
		{
			return null;
		}
		}catch (Exception e) {
			System.out.println("查询nextItem出错：" + e.getMessage());
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


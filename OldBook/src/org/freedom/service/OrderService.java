package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.freedom.model.Book;
import org.freedom.model.Category;
import org.freedom.model.Order;
import org.freedom.model.OrderItem;
import org.freedom.util.DbUtil;

/**
 *关于订单的service层，主要写搜索时的相关方法
 * @author Carver Wu
 *
 */
public class OrderService {
	private DbUtil dbUtil = new DbUtil();
	private Connection con;
	private ResultSet rs;

	public Order findOrderById(int id)
	{
		String sql = "select * from t_order where oid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Order order=new Order(rs.getInt ("oid"),rs.getInt ("uid"),rs.getInt ("status"),rs.getString ("time"));
				return order;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Order出错：" + e.getMessage());
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
	public Order findOrderByUser(int id)
	{
		String sql = "select * from t_order where uid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Order order=new Order(rs.getInt ("oid"),rs.getInt ("uid"),rs.getInt ("status"),rs.getString ("time"));
				return order;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Order出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Order findUserLastOrder(int id)
	{
		String sql = "select * from t_order where uid=?";//这里用sql语句写按oid降序具体怎么写我忘了
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Order order=new Order(rs.getInt ("oid"),rs.getInt ("uid"),rs.getInt ("status"),rs.getString ("time"));
				return order;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Order出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	public Order findNextOrder()
	{
		try{
		if (rs.next()) 
		{
			Order order=new Order(rs.getInt ("oid"),rs.getInt ("uid"),rs.getInt ("status"),rs.getString ("time"));
			return order;
		} 
		else
		{
			return null;
		}
		}catch (Exception e) {
			System.out.println("查询nextOrder出错：" + e.getMessage());
			return null;
		}
	}
	
	public int getOrderNum(int id)
	{
		int num=0;
		String sql = "select * from t_order where uid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			while (rs.next()) {
				num++;
			}
		} catch (Exception e) {
			System.out.println("查询Order出错：" + e.getMessage());
			return -1;
		} finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return num;
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


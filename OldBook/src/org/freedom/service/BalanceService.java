package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.freedom.model.Order;
import org.freedom.model.OrderItem;
import org.freedom.util.DbUtil;

public class BalanceService {

	private DbUtil dbUtil = new DbUtil();
	
	/**
	 * 创建订单
	 * @param uid
	 * @return
	 */
	public synchronized int createOrder(int uid){
		String sql1="insert into t_order(time,uid,status) values(?,?,?)";
		String sql2="select oid from t_order where uid=? and time=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int oid=-1;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql1);
			
			/*创建一个订单*/
			//System.out.println(uid);
			DateFormat df=DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			Date t=new Date();
			String mtime=df.format(t);
			pstmt.setString(1, mtime);
			pstmt.setInt(2, uid);
			pstmt.setInt(3, 0);
			pstmt.executeUpdate();
			closeStatement(pstmt);
			
			/*返回新生成的订单号*/
			pstmt = con.prepareStatement(sql2);
			pstmt.setInt(1, uid);
			pstmt.setString(2,mtime);
			rs=pstmt.executeQuery();
			while(rs.next()){
				oid=rs.getInt(1);
			}
			return oid;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return oid;
	}
	
	/**
	 * 查询某个订单的状态
	 * @param oid
	 * @return
	 */
	public Order getOrder(int oid){
		String sql ="select * from t_order where oid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Order order=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				order=new Order(rs.getInt("oid"),rs.getInt("uid"),rs.getInt("status"),rs.getString("time"));
			}
			return order;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return order;
	}
	
	/**
	 * 查询用户的所有订单
	 * @param uid
	 * @return
	 */
	public Collection<Order> getOrders(int uid){
		ArrayList<Order> orderList=new ArrayList<Order>();
		String sql ="select * from t_order where uid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				Order order=new Order(rs.getInt("oid"),rs.getInt("uid"),rs.getInt("status"),rs.getString("time"));
				orderList.add(order);
			}
			return orderList;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return orderList;
	}
	
	/**
	 * 获取某个订单的所有物品
	 * @param oid
	 * @return
	 */
	public Collection<OrderItem> getOrderItem(int oid){
		ArrayList<OrderItem> orderItemList=new ArrayList<OrderItem>();
		String sql = "select * from t_orderitem where oid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, oid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				OrderItem orderItem=new OrderItem(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4));
				orderItemList.add(orderItem);
			}
			return orderItemList;
		}
		catch(Exception e){
			System.out.println("查询订单物品出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return orderItemList;
	}
	/**
	 * 往订单中添加物品
	 * @param oid
	 * @param bid
	 * @param amount
	 */
	public synchronized boolean addOrderItem(int oid,int bid,int amount){
		String sql1 = "insert into t_orderitem values(NULL,?,?,?)";
		String sql2 = "update t_book set amount=amount-? where bid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, bid);
			pstmt.setInt(3, oid);
			pstmt.executeUpdate();
			closeStatement(pstmt);
			pstmt = con.prepareStatement(sql2);      //更改相应的书籍数量
			pstmt.setInt(1, amount);
			pstmt.setInt(2, bid);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeStatement(pstmt);
			closeConnection(con);
		}
		return false;
	}
	
	/**
	 * 删除订单
	 * @param oid
	 */
	public synchronized boolean deleteOrder(int oid){
		String sql1 = "delete from t_orderitem where oid=?";
		String sql2 = "delete from t_order where oid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			if(!backToCart(oid)) return false;
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql1);
			pstmt.setInt(1, oid);
			pstmt.executeUpdate();
			closeStatement(pstmt);
			pstmt= con.prepareStatement(sql2);
			pstmt.setInt(1, oid);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			closeStatement(pstmt);
			closeConnection(con);
		}
		return false;
	}
	
	/**
	 * 将订单的物品返回购物车
	 * @param oid
	 * @return
	 */
	public synchronized boolean backToCart(int oid){
		String sql1 = "select uid,bid,amount from t_orderitem where oid=?";
		String sql2 = "update t_book set amount=amount+? where bid=?";
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt1 = con.prepareStatement(sql1);
			pstmt2 = con.prepareStatement(sql2);
			pstmt1.setInt(1, oid);
			rs=pstmt1.executeQuery();
			while(rs.next()){
				int c_bid=rs.getInt(2);
				int c_amount=rs.getInt(3);
				pstmt2.setInt(1, c_amount);
				pstmt2.setInt(2, c_bid);
				pstmt2.executeUpdate();              //更新书籍的数量
			}
			return true;
		}
		catch(Exception e){
			System.out.println("订单结算出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt1);
			closeStatement(pstmt2);
			closeConnection(con);
		}
		return false;
	}
	
	/**
	 *完成订单支付
	 * @param oid
	 */
	public void purchase(int oid){
		
	}
	
	protected void closeConnection(Connection conn){
		if(conn!=null){
			try{
				conn.close();
				conn=null;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	protected void closeStatement(PreparedStatement pstmt){
		if(pstmt!=null){
			try{
				pstmt.close();
				pstmt=null;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	protected void closeResultSet(ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
				rs=null;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

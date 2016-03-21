package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import org.freedom.model.CartItem;
import org.freedom.util.DbUtil;

public class ShopCartService {

	private DbUtil dbUtil = new DbUtil();
	
	/**
	 * 获取用户购物车中的所有物品
	 * @param uid
	 * @return
	 */
	public Collection<CartItem> getCartItem(int uid){
		ArrayList<CartItem> cartItemList=new ArrayList<CartItem>();
		String sql = "select * from t_cartitem where uid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			//System.out.println("用户ID："+uid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int  cartItemId=rs.getInt(1);
				int bid=rs.getInt(3);
				int amount=rs.getInt(4);
				CartItem cartItem=new CartItem(cartItemId,uid,bid,amount);
				String name=findBookName(bid);
				float price=findBookPrice(bid);
				cartItem.setName(name);
				cartItem.setPrice(price);
				cartItemList.add(cartItem);
				//System.out.println("购物商品ID："+cartItemId);
			}
			return cartItemList;
		}
		catch(Exception e){
			System.out.println("查询购物车出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return cartItemList;
	}
	
	/**
	 * 获取单个物品
	 * @param cartItemId
	 * @return
	 */
	public CartItem getItem(int cartItemId){
		CartItem cartItem=new CartItem();
		String sql = "select * from t_cartitem where cartItemId=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartItemId);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int bid=rs.getInt(3);
				cartItem=new CartItem(rs.getInt(1),rs.getInt(2),bid,rs.getInt(4));
				String name=findBookName(bid);
				float price=findBookPrice(bid);
				cartItem.setName(name);
				cartItem.setPrice(price);
			}
			return cartItem;
		}
		catch(Exception e){
			System.out.println("查询购物车物品出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return cartItem;
	}
	
	/**
	 * 查找某本书的名字
	 * @param bid
	 * @return
	 */
	public String findBookName(int bid){
		String name=null;
		String sql = "select bookName from t_book where bid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				name=rs.getString(1);
			}
			return name;
		}
		catch(Exception e){
			System.out.println("查询购物车物品名称出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return name;
	}
	
	/**
	 * 查找某本书的价格
	 * @param bid
	 * @return
	 */
	public float findBookPrice(int bid){
		float price=0;
		String sql = "select price from t_book where bid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				price=rs.getFloat(1);
			}
			return price;
		}
		catch(Exception e){
			System.out.println("查询购物车物品价格出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return price;
	}
	/**
	 * 往用户的购物车中添加物品
	 * @param uid
	 * @param bid
	 * @param amount
	 */
	public synchronized boolean  addItem(int uid,int bid,int amount){
		if(!isAmountEnough(bid,amount)) return false;
		String sql = "insert into t_cartitem(uid,bid,amount) values(?,?,?)";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, bid);
			pstmt.setInt(3, amount);
			pstmt.executeUpdate();
			System.out.println("购物车添加success\n");
			return true;
		}
		catch(Exception e){
			System.out.println("添加购物车物品出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeStatement(pstmt);
			closeConnection(con);
		}
		return false;
	}
	
	/**
	 * 从用户购物车中删除物品
	 * @param uid
	 * @param bid
	 */
	public synchronized boolean deleteItem(int uid,int cartItemId){
		//String sql = "delete from t_cartitem where cartItemId = ? and  uid = ?";
		String sql = "delete from t_cartitem where cartItemId = ?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cartItemId);
			//pstmt.setInt(2, uid);	
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			System.out.println("删除购物车物品出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeStatement(pstmt);
			closeConnection(con);
		}
		return false;
	}
	
	/**
	 * 更新购物车中的物品
	 * @param cartItemId
	 * @param quantity
	 * @return
	 */
	public synchronized boolean updateCartItem(int cartItemId,int quantity){
		String sql = "update t_cartitem set amount=? where cartItemId=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, quantity);
			pstmt.setInt(2, cartItemId);
			pstmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			System.out.println("更新商品信息出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeStatement(pstmt);
			closeConnection(con);
		}
		return false;
	}
	
	public synchronized boolean gotoBalance(int oid,int uid,int cartItemId){
		if(oid<=0) return false;
		String sql = "select bid,amount from t_cartitem where uid=? and cartItemId=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, uid);
			pstmt.setInt(2, cartItemId);
			rs=pstmt.executeQuery();
			BalanceService mbalance=new BalanceService();
			while(rs.next()){
				if(!mbalance.addOrderItem(oid, rs.getInt(1), rs.getInt(2)))
					return false;
			}
			return true;
		}
		catch(Exception e){
			System.out.println("生成订单出错：" + e.getMessage());
			e.printStackTrace();
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return false;
	}
	
	
	/**
	 * 查询数据库中书的数量是否足够
	 * @param bid
	 * @param quantity
	 * @return
	 */
	public boolean isAmountEnough(int bid,int quantity){
		boolean isEnough=false;
		String sql = "select amount from t_book where bid=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con = dbUtil.getCon();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bid);
			rs=pstmt.executeQuery();
			while(rs.next()){
				int amount=rs.getInt("amount");
				if(amount>=quantity) isEnough=true;
			}
		}
		catch(Exception e){
			System.out.println("查询书数量出错：" + e.getMessage());
		}
		finally{
			closeResultSet(rs);
			closeStatement(pstmt);
			closeConnection(con);
		}
		return isEnough;
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

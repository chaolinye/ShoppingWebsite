package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.freedom.model.Shop;
import org.freedom.model.User;
import org.freedom.util.DbUtil;

/**
 *关于目录的service层，主要写搜索时的相关方法
 * @author Carver Wu
 *
 */
public class ShopService {
	private DbUtil dbUtil = new DbUtil();
	private Connection con;
	private ResultSet rs;

	public Shop findShopByName(String name)
	{
		String sql = "select * from t_shop where shopName=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new String(name.getBytes("ISO-8859-1"), "UTF-8"));
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Shop shop=new Shop(rs.getInt("sid"),rs.getString("loginName"),rs.getString("loginPassword"),
						rs.getString("shopName"),rs.getString("realName"),rs.getString("identity"),rs.getString("email"),
						rs.getString("address"),rs.getString("phoneNumber"),rs.getInt("credit"));
				return shop;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Shop出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Shop findShopByLoginName(String loginname)
	{
		String sql = "select * from t_shop where loginName=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new String(loginname.getBytes("ISO-8859-1"), "UTF-8"));
			System.out.println(pstmt);
			this.rs = pstmt.executeQuery();
			
			if (rs!=null&&rs.next()) {
				Shop shop=new Shop(rs.getInt("sid"),rs.getString("loginName"),rs.getString("loginPassword"),
						rs.getString("shopName"),rs.getString("realName"),rs.getString("identity"),rs.getString("email"),
						rs.getString("address"),rs.getString("phoneNumber"),rs.getInt("credit"));
				return shop;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询Shop出错：" + e.getMessage());
			return null;
		} finally {	}
	
	}
	
	public Shop findShopById(int sid)
	{
		String sql = "select * from t_shop where sid=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, sid);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				Shop shop=new Shop(rs.getInt("sid"),rs.getString("loginName"),rs.getString("loginPassword"),
						rs.getString("shopName"),rs.getString("realName"),rs.getString("identity"),rs.getString("email"),
						rs.getString("address"),rs.getString("phoneNumber"),rs.getInt("credit"));
				return shop;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询shop出错：" + e.getMessage());
			return null;
		} finally {
			
		}
	}
	
	public Shop findNextShop()
	{
		try{
		if (rs.next()) 
		{
			Shop shop=new Shop(rs.getInt("sid"),rs.getString("loginName"),rs.getString("loginPassword"),
					rs.getString("shopName"),rs.getString("realName"),rs.getString("identity"),rs.getString("email"),
					rs.getString("address"),rs.getString("phoneNumber"),rs.getInt("credit"));
			return shop;
		} 
		else
		{
			return null;
		}
		}catch (Exception e) {
			System.out.println("查询nextShop出错：" + e.getMessage());
			return null;
		}
	}
	
	public Shop login(Shop shop)
	{
		Shop currentShop=findShopByLoginName(shop.getloginName());
		if(currentShop==null){
			System.out.println("aaa");
			return null;
		}else if(!shop.getPassword().equals(currentShop.getPassword())){
			System.out.println("bbb");
			return null;
		}else{
			return currentShop;
		}
	}
	
	/**
	 * 查询某个用户名是否已经注册
	 * @param user
	 * @return
	 */
	public boolean isShopExisting(Shop shop){
		 
		if(findShopByLoginName(shop.getloginName())==null){
			 return false;
		 }else{
			 return true;
		 }
	}
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public Shop addShop(Shop shop) {
		String sql = "insert into t_shop(loginName,loginPassword,shopName,realName,identity,email,address,phoneNumber) values(?,?,?,?,?,?,?,?)";
		try{
			con=dbUtil.getCon();
            
			
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, shop.getloginName());
			pstmt.setString(2,shop.getPassword());
			pstmt.setString(3, shop.getName());
			pstmt.setString(4, shop.getRealName());
			pstmt.setString(5, shop.getIdentity());
			pstmt.setString(6, shop.getEmail());
			pstmt.setString(7, shop.getAddress());
			pstmt.setString(8, shop.getPhoneNumber());
			System.out.println("jjjj");
			System.out.println(pstmt);
			pstmt.executeUpdate();
			System.out.println("xxx");
			return findShopByLoginName(shop.getloginName());
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}catch(Exception e1){
			e1.printStackTrace();
			return null;
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public Shop register(Shop shop){
		
		Shop currentShop=findShopByLoginName(shop.getloginName());
		if(currentShop!=null){
			return null;
		}else{
			return addShop(shop);
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


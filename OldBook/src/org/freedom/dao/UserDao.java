package org.freedom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.freedom.model.User;
import org.freedom.util.DbUtil;

/**
 * User的dao层，主要实现user的数据库操作
 * 
 * @author chaolin
 *
 */
public class UserDao {

	private DbUtil dbUtil = new DbUtil();
	private Connection con;

	/**
	 * 根据用户名和密码查询用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public User findByUserName(String userName) {
		String sql = "select * from t_user where userName=?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUid(rs.getInt("uid"));
				user.setUserName(rs.getString("userName"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phoneNumber"));
				user.setIdentity(rs.getString("identity"));
				user.setRealName(rs.getString("realName"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询user出错：" + e.getMessage());
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

	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public User addUser(User user) {
		String sql = "insert into t_user values(null,?,?,?,?,?,?)";
		try{
			con=dbUtil.getCon();

			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2,user.getPassword());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getRealName());
			System.out.println(user.getRealName());
			pstmt.setString(6, user.getIdentity());
			System.out.println("jjjj");
			pstmt.executeUpdate();
			System.out.println("xxx");
			return findByUserName(user.getUserName());
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
}

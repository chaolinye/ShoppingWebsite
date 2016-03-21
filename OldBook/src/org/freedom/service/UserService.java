package org.freedom.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.freedom.dao.UserDao;
import org.freedom.model.Book;
import org.freedom.model.User;
import org.freedom.util.DbUtil;

/**
 * user的service层，主要写user的业务逻辑方法
 * @author chaolin
 *
 */
public class UserService {
	private UserDao userDao=new UserDao();
	private DbUtil dbUtil = new DbUtil();
	private Connection con;
	private ResultSet rs;
	/**
	 * 登录业务
	 * @param user
	 * @return
	 */
	public User login(User user){
		User currentUser=userDao.findByUserName(user.getUserName());
		if(currentUser==null){
			return null;
		}else if(!user.getPassword().equals(currentUser.getPassword())){
			return null;
		}else{
			return currentUser;
		}
	}
	
	/**
	 * 注册
	 * @param user
	 * @return
	 */
	public User register(User user){
		User currentUser=userDao.findByUserName(user.getUserName());
		if(currentUser!=null){
			return null;
		}else{
			return userDao.addUser(user);
		}
	}
	
	public User findUserById(int id)
	{
		String sql = "select * from t_user where id =?";
		try {
			con = dbUtil.getCon();
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			this.rs = pstmt.executeQuery();
			if (rs.next()) {
				User user=new User(rs.getInt("uid"), rs.getString ("userName"), rs.getString ("password"), rs.getString ("email"),
						rs.getString ("phone"), rs.getString ("realName"),rs.getString ("identity"));
				return user;
			} else {
				return null;
			}
		} catch (Exception e) {
			System.out.println("查询User出错：" + e.getMessage());
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
	 * 查询某个用户名是否已经注册
	 * @param user
	 * @return
	 */
	public boolean isUserExisting(User user){
		 if(userDao.findByUserName(user.getUserName())==null){
			 return false;
		 }else{
			 return true;
		 }
	}
}

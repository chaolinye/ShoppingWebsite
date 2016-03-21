package org.freedom.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * 数据库工具类
 * @author chaolin
 *
 */
public class DbUtil {
	private String dbUrl = "jdbc:mysql://localhost:3306/dbbook?useUnicode=true&amp;characterEncoding=UTF-8";
	private String dbUserName = "root";
	private String dbPassword = "123456";
	private String jdbcName = "com.mysql.jdbc.Driver";

	/**
	 * 获取数据库连接
	 * 
	 * @return
	 * @throws Exception
	 */
	public Connection getCon() throws Exception {
		Class.forName(jdbcName);
		Connection con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return con;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con) throws Exception {
		if (con != null) {
			con.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		try {
			Connection con=dbUtil.getCon();
			System.out.println("数据库连接成功");
			
			String sql = "insert into t_user values(null,?,?,?,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "freedom");
			pstmt.setString(2, "123456");
			pstmt.setString(3, "chaolin1994@163.com");
			pstmt.setString(4, "13536381441");
			pstmt.setString(5, "叶子");
			pstmt.setString(6, "440883199902126512");
			pstmt.executeQuery();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package org.freedom.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.freedom.util.DbUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * JUnit测试类，测试用的
 * @author chaolin
 *
 */
public class DbTest {
	DbUtil dbUtil=new DbUtil();
	Connection con;
	@Before
	public void setUp() throws Exception {
		con=dbUtil.getCon();
	}

	@After
	public void tearDown() throws Exception {
		dbUtil.closeCon(con);
	}

	@Test
	public void test() throws Exception{
		String sql = "insert into t_user values(null,?,?,?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, "freedom");
		pstmt.setString(2, "123456");
		pstmt.setString(3, "chaolin1994@163.com");
		pstmt.setString(4, "13536381441");
		pstmt.setString(5, "叶子");
		pstmt.setString(6, "440883199902126512");
		pstmt.executeUpdate();
	}
	
//	@Test
//	public void test1()throws Exception{
//		String sql="select * from t_user";
//		Statement stmt=con.createStatement();
//		ResultSet rs=stmt.executeQuery(sql);
//		while(rs.next()){
//			System.out.println(rs.getString("realName"));
//		}
//	}
}

package org.freedom.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DatabaseConn {
	public String DBDRIVER = "com.mysql.jdbc.Driver";
	public String DBURL = "jdbc:mysql://localhost:3306/dbbook?useUnicode=true&amp;characterEncoding=UTF-8";
	public String DBUSER = "root";
	public String DBPASS = "123456";

private Connection conn = null;
private Statement stmt = null;
private ResultSet rs = null;

/**
 * ��������
 */
public DatabaseConn(){
	try {
		Class.forName(DBDRIVER);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("��������ʧ��!"+e);
	}
}

/**
 * �������ݿ�
 * @return
 */
public Connection getConnection(){
	try {
		conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("���ݿ�����ʧ��!");
	}
	return conn;
}

/**
 * ��ѯ��¼����
 * 
 * 
 */
public ResultSet executeQuery(String sql){
	conn = getConnection();
	try {
		stmt = conn.createStatement();
		rs = stmt.executeQuery(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(rs == null){
		System.out.println("ִ�в�ѯ����ʧ�ܣ�");
	}
	return rs;		
}

/**
 * �޸ģ�ɾ�������¼�¼����
 * @param sql
 * @return
 */
public int executeUpdate(String sql){
	int result = 0;
	conn = getConnection();
	try {
		stmt = conn.createStatement();
		result = stmt.executeUpdate(sql);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println("ִ��ʧ�ܣ�");
		result = 0;
	}
	return result;//ִ��Ӱ�������
}

/**
 * �ر����ݿ�
 */
public void close(){
	if(rs != null){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(stmt != null){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	if(conn != null){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
}


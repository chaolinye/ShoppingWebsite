package org.freedom.model;

import org.freedom.service.UserService;

/**
 * 书籍model类
 * @author Carve Wu
 *
 */

public class Order {
	private int oid;
	private String time;
	private int uid;
	private int status;
	
	
	
	public Order(){
		super();
	}
	
	public Order(int oid,int uid,int status,String time)
	{
		super();
		this.oid = oid;
		this.uid = uid;
		this.status = status;
		this.time = time;
		
	}



	public int getOid() {
		return oid;
	}
	public int getUid() {
		return uid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getTime() {
		return time;
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public User findUser()
	{
		UserService ser=new UserService();
		User user=ser.findUserById(uid);
		return user;
	}
}

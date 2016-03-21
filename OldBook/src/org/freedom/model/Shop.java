package org.freedom.model;


/**
 *商家model类
 * @author Carve Wu
 *
 */
public class Shop {
	private int sid;
	private String loginName;
	private String loginPassword;
	private String shopName;
	private String realName;
	private String identity;
	private String email;
	private String address;
	private String phoneNumber;
	private int credit;
	
	
	
	public Shop(){
		super();
	}
	
	public Shop(int sid,String loginName,String loginPassword,
		String shopName,String realName,String identity,String email,
		String address,String phoneNumber,int credit)
	{
		super();
		this.sid = sid;
		this.loginName = loginName;
		this.loginPassword = loginPassword;
		this.shopName = shopName;
		this.realName = realName;
		this.identity = identity;
		this.email = email;
		this.sid = sid;
		this.address = address;
		this.phoneNumber=phoneNumber;
		this.credit = credit;
	}
	public Shop(String loginName,String loginPassword,
			String shopName,String realName,String identity,String email,
			String address,String phoneNumber)
		{
			super();
			this.loginName = loginName;
			this.loginPassword = loginPassword;
			this.shopName = shopName;
			this.realName = realName;
			this.identity = identity;
			this.email = email;
			this.sid = sid;
			this.address = address;
			this.phoneNumber=phoneNumber;
		
		}
	public Shop(String userName, String password) {
		super();
		this.loginName = userName;
		this.loginPassword = password;
	}
	
	public Shop(String loginName) {
		super();
		this.loginName = loginName;
	}

	public int getSid() {
		return sid;
	}
	public int getCredit() {
		return credit;
	}
	public String getName() {
		return shopName;
	}
	public String getRealName() {
		return realName;
	}
	public String getIdentity() {
		return identity;
	}
	public String getAddress() {
		return address;
	}
	public String getEmail() {
		return email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getloginName() {
		return loginName;
	}
	public String getPassword() {
		return loginPassword;
	}
	public void setName(String name) {
		this.shopName=name;
	}
	
	public void setLoginName(String name) {
		this.loginName=name;
	}
	public void setPassword(String pass) {
		this.loginPassword=pass;
	}

}

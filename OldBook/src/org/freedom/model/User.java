package org.freedom.model;
/**
 * 用户model类
 * @author chaolin
 *
 */
public class User {
	private int uid;
	private String userName;
	private String password;
	private String email;
	private String phone;
	private String realName;
	private String identity;
	
	public User(){
		super();
	}
	
	public User(int uid, String userName, String password, String email, String phone, String realName,
			String identity) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.realName = realName;
		this.identity = identity;
	}

	public User(String userName, String password, String email, String phone, String realName, String identity) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.realName = realName;
		this.identity = identity;
	}

	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	
	
	public User(String userName) {
		super();
		this.userName = userName;
	}

	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}


	@Override
	public String toString() {
		return "User [uid=" + uid + ", userName=" + userName + ", password=" + password + ", email=" + email
				+ ", phone=" + phone + ", realName=" + realName + ", identity=" + identity + "]";
	}
	
	
}

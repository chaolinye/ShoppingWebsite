package org.freedom.model;
/**
 * 书籍分类model类
 * @author Carve Wu
 *
 */
public class Category {
	private int cid;
	private String name;
	private String synopsis;
	
		
	public Category(){
		super();
	}
	
	public Category(int cid,String name,String synopsis)
	{
		super();
		this.cid = cid;
		this.name = name;
		this.synopsis = synopsis;
	}
	public Category(int cid,String name)
	{
		super();
		this.cid = cid;
		this.name = name;
		this.synopsis = "";
	}


	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getCaName() {
		return name;
	}
	public String getSynopsise() {
		return synopsis;
	}

}

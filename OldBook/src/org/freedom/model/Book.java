package org.freedom.model;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.freedom.service.CategoryService;
import org.freedom.service.ShopService;

/**
 * 书籍model类
 * @author Carve Wu
 *
 */
public class Book {
	private int bid;
	private String bookName;
	private String author;
	private String press;
	private String publicationDate;
	private int popularity;
	private String synopsis;
	private int sid;
	private int amount;
	private int cid;
	private float price;
	private float currPrice;
	private String image;
	
	
	
	public Book(){
		super();
	}
	
	public Book(int bid,String bookName,String author,String press,
			String publicationDate,int popularity,String synopsis,
			int sid,int amount,int cid,float price,float currPrice,String image)
	{
		super();
		this.bid = bid;
		this.bookName = bookName;
		this.author = author;
		this.press = press;
		this.publicationDate = publicationDate;
		this.popularity = popularity;
		this.synopsis = synopsis;
		this.sid = sid;
		this.amount = amount;
		this.cid=cid;
		this.price = price;
		this.currPrice = currPrice;
		this.image = image;
	}


	public int getSid(){
		return sid;
	}
	public void setSid(int i){
		sid=i;
	}
	public int getBid() {
		return bid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int i){
		cid=i;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getBookName() {
		return bookName;
	}
	public String getPress() {
		return press;
	}
	public void setPress(String pr){
		press=pr;
	}
	public void setAuthor(String au){
		author=au;
	}
	public String getAuther() {
		return author;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public float getPrice() {
		return currPrice;
	}
	public void setPrice(int pr){
		price=pr;
	}
	public void setImage(String im){
		image=im;
	}
	public void setCurrPrice(int pr){
		currPrice=pr;
	}
	public int getAmount()
	{
		return amount;
	}
	public void setAmount(int nu){
		amount=nu;
	}
	public String getPublicationDate()
	{
		return publicationDate;
	}
	public void setPublicationDate(String ti){
		publicationDate=ti;
	}
	public String getSynopsis()
	{
		return synopsis;
	}
	public void setSynopsis(String re){
		synopsis=re;
	}
	public String getImage()
	{
		return image;
	}
	public int getPopularity()
	{
		return popularity;
	}
	public void setPopularity(int po){
		popularity=po;
	}
	public String bookS()
	{
		String str;
		str="书名："+bookName+"  价格:"+String.valueOf(currPrice)+" 作者： "+author;
		return str;
	}
	
	public String getbookShop()
	{
		String str;
		ShopService ser=new ShopService();
		Shop shop=ser.findShopById(sid);
		str=shop.getName();
		ser.closeService();
		return str;
	}
	public int getbookShopCredit()
	{
		String str;
		ShopService ser=new ShopService();
		Shop shop=ser.findShopById(sid);		
		ser.closeService();
		return shop.getCredit();
	}
}

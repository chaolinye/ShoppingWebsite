package org.freedom.model;

import java.io.Serializable;

public class CartItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int cartItemId;
	private int uid;
	private int bid;
	private int amount;
	private String name;
	private float price;
	
	public CartItem(){
	}
	public CartItem(int cartItemId,int uid,int bid,int amount){
		this.cartItemId=cartItemId;
		this.uid=uid;
		this.bid=bid;
		this.amount=amount;
		this.name=null;
		this.price=0;
	}
	public int getCartItemId(){
		return cartItemId;
	}
	public int getUid(){
		return uid;
	}
	public int getBid(){
		return bid;
	}
	public int getAmount(){
		return amount;
	}
	public void setAmount(int amount){
		this.amount=amount;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
	public float getPrice(){
		return price;
	}
	public void setPrice(float price){
		this.price=price;
	}
}

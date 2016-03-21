package org.freedom.model;

import org.freedom.service.OrderService;
import org.freedom.service.SearchingService;


/**
 *订单物品model类
 * @author Carve Wu
 *
 */

public class OrderItem {
	private int orderItemId;
	private int amount;
	private int bid;
	private int oid;
	
	
	
	public OrderItem(){
		super();
	}
	
	public OrderItem(int orderItemId,int amount,int bid,int oid)
	{
		super();
		this.oid = oid;
		this.bid = bid;
		this.amount = amount;
		this.orderItemId = orderItemId;
		
	}



	public int getOid() {
		return oid;
	}
	public int getBid() {
		return bid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public int getOrderItemId() {
		return orderItemId;
	}
	public int getAmount() {
		return amount;
	}

	public void setAmount(int status) {
		this.amount = status;
	}

	public Book findBook()
	{
		SearchingService ser=new SearchingService();
		Book book=ser.findbookById(bid);
		return book;
	}
	
	public Order findOrder()
	{
		OrderService ser=new OrderService();
		Order order=ser.findOrderById(oid);
		return order;
	}
	
}

package org.freedom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.freedom.model.CartItem;
import org.freedom.model.Order;
import org.freedom.model.User;
import org.freedom.service.BalanceService;
import org.freedom.service.ShopCartService;

@WebServlet("/balance")
public class BalanceServlet extends HttpServlet{

	private BalanceService balanceService=new BalanceService();
	private ShopCartService cart=new ShopCartService();
	int uid=-1;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String reqAction=req.getParameter("action");
		String targetURL="balance.jsp";
		User sUid=(User)req.getSession().getAttribute("user");
		
		if(sUid==null){
			//targetURL="/";
			System.out.println("用户没有登录!");}
		else{
			uid=sUid.getUid();}
		if(reqAction==null){                               //设置默认动作是显示选入订单的商品
			reqAction="showorder";
			//System.out.println("不正确的操作!");
		}
		uid=1;
		if(reqAction.equals("deleteoItem")){                  //删除选中列表中的某个商品
			Collection<CartItem> items=(Collection<CartItem>)req.getSession().getAttribute("items");
			int itemId=Integer.parseInt(req.getParameter("orderItemId"));
			CartItem c_item=null;
			Iterator<CartItem> item=items.iterator();
			while(item.hasNext()){
				CartItem cartitem=(CartItem)item.next();
				int cartItemId=cartitem.getCartItemId();
				if(cartItemId==itemId) c_item=cartitem;
			}
			if(c_item!=null) items.remove(c_item);
			else System.out.println("删除商品失败");
			req.getSession().setAttribute("items", items);
		}
		else if(reqAction.equals("balance")){                   //选中即将加入订单的商品
			String[] itemNo=(String[])req.getParameterValues("itemNo");
			if(itemNo==null||itemNo.length<=0){             //判定是否有选择商品
				System.out.println("没有选择商品!");
				targetURL=req.getContextPath()+"/shopcart";
				resp.sendRedirect(targetURL);
				return;
			}
			int length=itemNo.length;
			int[] cartId=new int[length];
			for(int k=0;k<length;k++){
				cartId[k]=Integer.parseInt(itemNo[k]);
			}
			ArrayList<CartItem> items=new ArrayList<CartItem>();
			for(int i=0;i<length;i++){
				CartItem item=cart.getItem(cartId[i]);                  //获取选中商品的信息
				items.add(item);
			}
			req.getSession().setAttribute("items", items);             //保存选中的商品
			req.getRequestDispatcher(targetURL).forward(req, resp);
			return;
		}
		else if(reqAction.equals("confirm")){                   //确认生成订单
			Collection<CartItem> items=(Collection<CartItem>)req.getSession().getAttribute("items");
			if(items==null||items.size()<=0){
				System.out.println("订单结算错误!");
				req.getRequestDispatcher(targetURL).forward(req, resp);
				return;
			}
			BalanceService mbalance=new BalanceService();
			int oid_new=mbalance.createOrder(uid);                      //创建一个订单
			if(oid_new==-1){
				System.out.println("创建订单失败！");
				req.getRequestDispatcher(targetURL).forward(req, resp);
				return;
			}
			boolean flag=true;
			Iterator<CartItem> item=items.iterator();
			while(item.hasNext()){                                          //将选中的商品加入新生成的订单中
				CartItem cartitem=(CartItem)item.next();
				int cartbid=cartitem.getBid();
				int cartamount=cartitem.getAmount();
				if(!mbalance.addOrderItem(oid_new, cartbid, cartamount)){
					mbalance.deleteOrder(oid_new);                  //添加商品失败，删除订单，撤销此次操作
					flag=false;
					break;
				}
			}
			if(flag){               //订单生成成功
				Iterator<CartItem> ditem=items.iterator();
				while(ditem.hasNext()){                                    //删除已经加入订单的购物车商品
					CartItem cartitem=(CartItem)ditem.next();
					int cartItemId=cartitem.getCartItemId();
					int cartuid=cartitem.getUid();
					 if(!cart.deleteItem(cartuid, cartItemId)){
					    	System.out.println("删除购物车物品失败!");}
				}
			}
			else{
				System.out.println("订单添加物品失败！");
				targetURL=req.getContextPath()+"/shopcart";
				resp.sendRedirect(targetURL);
				return;
			}
			targetURL=req.getContextPath()+"/balance";
			resp.sendRedirect(targetURL);
			return;
		}
		else{                    //默认输出用户所有订单
			targetURL="orders.jsp";
			Collection<Order> orders=balanceService.getOrders(uid);
			System.out.println("orders success");
			req.getSession().setAttribute("orders", orders);
		}
		req.getRequestDispatcher(targetURL).forward(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

package org.freedom.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.freedom.model.CartItem;
import org.freedom.model.User;
import org.freedom.service.ShopCartService;

@WebServlet("/shopcart")
public class ShopCartServlet extends HttpServlet{

	private ShopCartService cart=new ShopCartService();
	String targetURL="shopping_cart.jsp";
	int uid=-1;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShopCartServlet(){
		super();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String reqAction=req.getParameter("action");
		User sUid=(User)req.getSession().getAttribute("user");
		System.out.println("购物车请求");
		if(sUid==null){
			//targetURL="/";
			System.out.println("用户没有登录!");
			req.setAttribute("login", "no");
			req.getRequestDispatcher("book_msg.jsp").forward(req, resp);
			return;
		}
		else{
			uid=sUid.getUid();}
		if(reqAction==null){
			reqAction="showcart";
			//System.out.println("不正确的操作!");
		}
		if(reqAction.equals("addItem")){                                   //添加商品
			System.out.println("加入购物车请求");
			int bid=Integer.parseInt(req.getParameter("bid"));
			int amount=Integer.parseInt(req.getParameter("amount"));
			if(cart.addItem(uid, bid, amount)){
				System.out.println("添加商品success");
			}else{
				System.out.println("添加商品失败");
			}
		}
		else if(reqAction.equals("deleteItem")){                       //删除商品
			int cartItemId=Integer.parseInt(req.getParameter("cartItemId"));
			if(!cart.deleteItem(uid, cartItemId)){
				System.out.println("删除商品"+cartItemId+"失败");
			}
		}
		else if(reqAction.equals("deleteCart")){
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
			for(int k=0;k<length;k++){
				if(!cart.deleteItem(uid, cartId[k]))
					System.out.println("删除商品"+cartId[k]+"失败");
			}
		}
		else if(reqAction.equals("saveCart")){                      //保存购物车状态
			int i=0;
			Collection<CartItem> old_shopcart=cart.getCartItem(uid);
			Iterator<CartItem> item=old_shopcart.iterator();
			while(item.hasNext()){
				String strNum=req.getParameter("num_"+i);
				String strBookId=req.getParameter("book_"+i);
				
				int quantity=Integer.parseInt(strNum);
				int bookId=Integer.parseInt(strBookId);
				
				CartItem cartitem=(CartItem)item.next();
				if(quantity>0&&cart.isAmountEnough(bookId, quantity)){ 
					//System.out.println("bookid="+bookId);
					int old_amount=cartitem.getAmount();
					if(old_amount!=quantity){                             //商品数量已更改
						int cartItemId=cartitem.getCartItemId();
						if(!cart.updateCartItem(cartItemId, quantity))
							System.out.println("更新商品数量出错！");
					}
				}
				i++;
				}
		}
		else {                                                                       //显示购物车商品
			System.out.println("初始情况~");
		}
		Collection<CartItem> shopcart=cart.getCartItem(uid);
		req.getSession().setAttribute("shopcart", shopcart);
		req.getRequestDispatcher(targetURL).forward(req, resp);
		return;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

}

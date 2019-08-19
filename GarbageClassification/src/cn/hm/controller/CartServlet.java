package cn.hm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hm.bean.Cart;
import cn.hm.bean.CartItem;
import cn.hm.bean.Goods;
import cn.hm.bean.Provinces;
import cn.hm.bean.StoreOrder;
import cn.hm.bean.User;
import cn.hm.service.CartItemService;
import cn.hm.service.CartService;
import cn.hm.service.GoodsService;
import cn.hm.service.StoreOrderService;
import cn.hm.service.impl.CartItemServiceImpl;
import cn.hm.service.impl.CartServiceImpl;
import cn.hm.service.impl.GoodsServiceImpl;
import cn.hm.service.impl.StoreOrderServiceImpl;
import cn.hm.util.BaseServlet;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private GoodsService gservice = new GoodsServiceImpl();   
    private CartItemService ciService = new CartItemServiceImpl();
    private CartService cService = new CartServiceImpl();
   
    
    protected String buyNow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int gid = (int) request.getAttribute("gid");
		int count = (int) request.getAttribute("count");
		List<Provinces> plist = (List<Provinces>) request.getAttribute("list");
		request.setAttribute("plist", plist);
		Goods goods = gservice.getGoodsById(gid);
		request.setAttribute("goods", goods);
    	return "/store/buyNow.jsp";
    }
    
	protected String addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		CartItem cartItem = new CartItem();
		User user = (User) request.getSession().getAttribute("user");;
		int uid = user.getUid();
		Cart cart = cService.getMyCart(uid);
		int gid = (int) request.getAttribute("gid");
		int count = (int) request.getAttribute("count");
		List<Provinces> plist = (List<Provinces>) request.getAttribute("list");
		request.setAttribute("plist", plist);
		Goods goods = gservice.getGoodsById(gid);
		cartItem.setGoods(goods);
		cartItem.setCartid(cart.getCartid());
		try {
			ciService.updateAll(cart.getCartid(), cartItem, count);			
			cart = cService.getMyCart(uid);
			List<CartItem> list = ciService.findItem(cart.getCartid());
			cart = cService.getMapData(cart, list);
			request.setAttribute("cart", cart);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "/store/cart.jsp";
	}
	
	protected String clearAllItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");;
		request.getSession().getAttribute("user");
		int uid = user.getUid();
		Cart cart = cService.getMyCart(uid);
		cService.clearMyCart(cart.getCartid());
		return "/store/cart.jsp";
	}
	
	protected String getMyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = (User) request.getSession().getAttribute("user");;
		request.getSession().getAttribute("user");
		int uid = user.getUid();
		Cart cart = cService.getMyCart(uid);
		int cartId = cart.getCartid();
		List<CartItem> items = ciService.findItem(cartId);
		cart = cService.getMapData(cart, items);
		request.setAttribute("cart", cart);
		List<Provinces> plist = (List<Provinces>) request.getAttribute("list");
		request.setAttribute("plist", plist);
		return "/store/cart.jsp";
	}
	
	

}

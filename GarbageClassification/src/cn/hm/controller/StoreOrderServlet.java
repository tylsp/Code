package cn.hm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hm.bean.Cart;
import cn.hm.bean.CartItem;
import cn.hm.bean.Goods;
import cn.hm.bean.StoreOrder;
import cn.hm.bean.StoreOrderItem;
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

@WebServlet("/StoreOrderServlet")
public class StoreOrderServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private StoreOrderService storeService = new StoreOrderServiceImpl();
	private CartService cartService = new CartServiceImpl();
	private CartItemService itemService = new CartItemServiceImpl();
	private GoodsService goodService = new GoodsServiceImpl();
	private CartItemService cartItemService = new CartItemServiceImpl();

	private String getCode() {
		String code = UUID.randomUUID().toString();
		code = code.replaceAll("-", "").toUpperCase();
		return code;
	}

	protected String addStoreOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取uid(session中获取)
		User user = (User) request.getSession().getAttribute("user");;
		request.getSession().getAttribute("user");
		int uid = user.getUid();
		Collection<CartItem> list = new ArrayList<CartItem>();
		int cartId = Integer.valueOf(request.getParameter("cartid"));
		StoreOrder order = new StoreOrder();
		order.setOid(getCode());
		order.setOrdertime(new Date());
		String[] values = request.getParameterValues("gid");
		String message = null;
		if (values == null) {
			message = "您没有选择商品";
			request.setAttribute("message", message);
			return "/store/order.jsp";
		} else {
			double total = 0;
			for (String value : values) {
				CartItem item = itemService.finItemById(cartId, Integer.valueOf(value));
				Goods good = goodService.getGoodsById(Integer.valueOf(value));
				item.setGoods(good);
				list.add(item);
				total = total + item.getCtotal();
				cartItemService.deleteItem(cartId, Integer.valueOf(value));
			}
			order.setOtotal(total);
		}
		order.setUid(uid);
		String province = request.getParameter("provice");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String address = province + city + area;
		String phone = request.getParameter("phone");
		order.setPhone(phone);
		order.setAddress(address);
		String deaddress = request.getParameter("deaddress");
		order.setDeaddress(deaddress);
		List<StoreOrderItem> items = new ArrayList<>();
		for (CartItem item : list) {
			StoreOrderItem so = new StoreOrderItem();
			so.setOsubtotal(item.getCtotal());
			so.setCount(item.getCount());
			so.setOid(order.getOid());
			so.setGoods(item.getGoods());
			items.add(so);
		}
		order.setList(items);
		storeService.createStoreOrder(order);
		System.out.println("StoreOrderServlet 103 order" + order);
		request.setAttribute("order", order);
		double sum = cartItemService.getTotal(cartId);
		try {
			cartService.updateTotal(cartId, sum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/store/order.jsp";
	}

	protected String buy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int gid = Integer.valueOf(request.getParameter("gid"));
		Goods good = goodService.getGoodsById(gid);
		StoreOrder order = new StoreOrder();
		order.setOid(getCode());
		order.setOrdertime(new Date());
		order.setOtotal(good.getGprice());
		String province = request.getParameter("provice");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String address = province + city + area;
		String phone = request.getParameter("phone");
		order.setPhone(phone);
		order.setAddress(address);
		String deaddress = request.getParameter("deaddress");
		order.setDeaddress(deaddress);
		User user = (User) request.getSession().getAttribute("user");;
		request.getSession().getAttribute("user");
		int uid = user.getUid();
		order.setUid(uid);
		List<StoreOrderItem> items = new ArrayList<>();
		StoreOrderItem so = new StoreOrderItem();
		so.setOsubtotal(good.getGprice());
		so.setCount(1);
		so.setOid(order.getOid());
		so.setGoods(good);
		items.add(so);
		order.setList(items);
		storeService.createStoreOrder(order);
		request.setAttribute("order", order);
		return "/store/order.jsp";
	}
	protected String updateState(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");;
		request.getSession().getAttribute("user");
		int uid = user.getUid();
		String oid = request.getParameter("oid");
		// 已支付状态
		int ostate = 1;
		boolean bool = storeService.updateState(oid, ostate);
		if (bool) {
			List<StoreOrder> order = storeService.getStoreOrder(uid, ostate);
			request.setAttribute("order", order);
			return "/store/backStore.html";
		}
		return null;
	}
	
	/*protected String myPersonal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = (User) request.getSession().getAttribute("user");;
		request.getSession().getAttribute("user");
		int uid = user.getUid();
		List<StoreOrder> orders = storeService.getStoreOrder(uid, -1);
		request.setAttribute("orders", orders);
		return "/store/allorders.jsp";
	}*/
	
	protected String deleteOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String oid = request.getParameter("oid");
		boolean bool = storeService.deleteOrder(oid);
		if(bool) {
			
			/*myPersonal(request, response);*/
			return "/store/backStore.html";
		}
		return null;
	}
	
}

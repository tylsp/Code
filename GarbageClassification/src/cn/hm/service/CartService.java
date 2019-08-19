package cn.hm.service;

import java.sql.SQLException;
import java.util.List;

import cn.hm.bean.Cart;
import cn.hm.bean.CartItem;

public interface CartService {
	//判断用户是否已有购物车
	boolean isExistCart(int uid);
	//有购物车，直接获取
	Cart getCart(int uid);
	//没有购物车，创建，
	boolean createCart(double total,int uid);
	//获取购物车
	public Cart getMyCart(int uid);
	// 向购物车中添加条目
	public Cart getMapData(Cart cart,List<CartItem> list);
	//修改总金额
	boolean updateTotal(int cartId,double total) throws SQLException;
	//情况购物车
	public void clearMyCart(int cartId);
}

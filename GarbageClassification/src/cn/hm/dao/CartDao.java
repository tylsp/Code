package cn.hm.dao;

import java.sql.SQLException;

import cn.hm.bean.Cart;

public interface CartDao {
	//判断用户是否已有购物车
	boolean isExistCart(int uid);
	//有购物车，直接获取
	Cart getCart(int uid);
	//没有购物车，创建，
	boolean createCart(double total,int uid);
	//修改总金额
	boolean updateTotal(int cartId,double total) throws SQLException;
}

package cn.hm.dao;

import java.sql.SQLException;
import java.util.List;

import cn.hm.bean.CartItem;

public interface CartItemDao {
	//添加条目
	boolean addItem(CartItem cartItem);
	//删除条目
	boolean deleteItem(int cartId,int gid);
	//更改数量
	boolean updateCount(int cid,int count);
	//更改小计
	boolean updateCtotal(int cid,double total);
	//查询条目
	List<CartItem> findItem(int cartId);
	//查看是否已存在指定购物车id和商品id的条目
	CartItem finItemById(int cartId,int gid);
	//获取小计
	double getTotal(int cartId);
	//删除所有条目
	boolean deleteAllItem(int cartId) throws SQLException;
	//根据商品id(gid)获取条目
	CartItem getItemByGid(int gid);
	
	
}

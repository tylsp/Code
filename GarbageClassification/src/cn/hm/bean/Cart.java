package cn.hm.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	// 购物车id
	private int cartid;
	// 购物车总计
	private double total;
	
	
	private Map<Integer,CartItem> map = new  LinkedHashMap<Integer,CartItem>();

	public int getCartid() {
		return cartid;
	}

	
	public Map<Integer, CartItem> getMap() {
		return map;
	}


	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public void setCartid(int cartid) {
		this.cartid = cartid;
	}

	//清空购物车
	public void clear() {
		map.clear();
	}
	
	//添加条目
	public void addCartItem(CartItem item) {
		//判断此条目是否已经存在
		if(map.containsKey(item.getGoods().getGid())) {
			
			//如果包含此条目 得到原来的数量
			int old  = map.get(item.getGoods().getGid()).getCount();
			BigInteger num1 = new BigInteger(old + "");
			BigInteger num2 = new BigInteger(item.getCount() + "");
			item.setCount((num1.add(num2)).intValue());
		}
		map.put(item.getGoods().getGid(), item);
	}
	
	//删除指定条目
	public void removeCartItem(int gid) {
		map.remove(gid);
	}
	
	//获取我的购物车
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	public double getTotal() {
		BigDecimal num1 = new BigDecimal(total + "");
		for(CartItem item:map.values()) {
			num1.add(new BigDecimal(item.getCtotal() + ""));
		}
		total = num1.doubleValue();
		return total;
	}
	
	//数量改变
	public void changeCount(int gid,int count) {
		CartItem item = map.get(gid);
		count = item.getCount() + count;
		if(count < 0) {
			removeCartItem(gid);
			return;
		}
		item.setCount(count);
	}


	@Override
	public String toString() {
		return "Cart [cartid=" + cartid + ", total=" + total + ", map=" + map + "]";
	}
	
	
}

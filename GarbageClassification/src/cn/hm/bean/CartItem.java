package cn.hm.bean;

import java.math.BigDecimal;

public class CartItem {
	// 购物车条目id
	private int cid;
	// 购物车id
	private int cartid;
	// 商品数量
	private int count;
	// 条目总计
	private double ctotal;
	private Goods goods;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getCartid() {
		return cartid;
	}
	public void setCartid(int cartid) {
		this.cartid = cartid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCtotal() {
		BigDecimal price = new BigDecimal(this.goods.getGprice());
		BigDecimal count = new BigDecimal(this.count);
		ctotal = price.multiply(count).doubleValue();
		return ctotal;
	}
	public void setCtotal(double ctotal) {
		this.ctotal = ctotal;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "CartItem [cid=" + cid + ", cartid=" + cartid + ", count=" + count + ", ctotal=" + ctotal + ", goods="
				+ goods + "]";
	}

	

}

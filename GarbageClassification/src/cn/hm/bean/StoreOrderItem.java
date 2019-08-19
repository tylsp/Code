package cn.hm.bean;

public class StoreOrderItem {
	private int orderitemid;
	private int count;
	private double osubtotal;
	private String oid;
	private Goods goods;
	public int getOrderitemid() {
		return orderitemid;
	}
	public void setOrderitemid(int orderitemid) {
		this.orderitemid = orderitemid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getOsubtotal() {
		return osubtotal;
	}
	public void setOsubtotal(double osubtotal) {
		this.osubtotal = osubtotal;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}

	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "StoreOrderItem [orderitemid=" + orderitemid + ", count=" + count + ", osubtotal=" + osubtotal + ", oid="
				+ oid + ", goods=" + goods + "]";
	}
	
	
	
}

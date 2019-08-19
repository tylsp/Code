package cn.hm.bean;

public class CallOrderItem {

	private String iid;
	private String oid;
	private CallItem CallItem;
	private double iweight;
	private double subtotal;

	public String getIid() {
		return iid;
	}

	public void setIid(String iid) {
		this.iid = iid;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public CallItem getCallItem() {
		return CallItem;
	}

	public void setCallItem(CallItem callItem) {
		CallItem = callItem;
	}

	public double getIweight() {
		return iweight;
	}

	public void setIweight(double iweight) {
		this.iweight = iweight;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "CallOrderItem [iid=" + iid + ", oid=" + oid + ", CallItem=" + CallItem + ", iweight=" + iweight
				+ ", subtotal=" + subtotal + "]";
	}

}

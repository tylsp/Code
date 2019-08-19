package cn.hm.bean;

public class CallItem {

	private int cid;
	private String cname;
	private String cimage;
	private double cprice;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCimage() {
		return cimage;
	}

	public void setCimage(String cimage) {
		this.cimage = cimage;
	}

	public double getCprice() {
		return cprice;
	}

	public void setCprice(double cprice) {
		this.cprice = cprice;
	}

	public CallItem(int cid, String cname, String cimage, double cprice) {
		this.cid = cid;
		this.cname = cname;
		this.cimage = cimage;
		this.cprice = cprice;
	}

	public CallItem() {
	}

	@Override
	public String toString() {
		return "CallItem [cid=" + cid + ", cname=" + cname + ", cimage=" + cimage + ", cprice=" + cprice + "]";
	}

}

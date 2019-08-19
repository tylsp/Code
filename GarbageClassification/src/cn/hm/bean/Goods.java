package cn.hm.bean;

public class Goods {
	//商品id
	private int gid;
	//商品名称
	private String gname;
	//商品价格
	private double gprice;
	//商品所需积分
	private int credit;
	//商品图片路径
	private String image;
	//商品详情
	private String detial;
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	public double getGprice() {
		return gprice;
	}
	public void setGprice(double gprice) {
		this.gprice = gprice;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDetial() {
		return detial;
	}
	public void setDetial(String detial) {
		this.detial = detial;
	}
	public Goods() {
		
	}
	public Goods(int gid, String gname, double gprice, int credit, String image, String detial) {
		super();
		this.gid = gid;
		this.gname = gname;
		this.gprice = gprice;
		this.credit = credit;
		this.image = image;
		this.detial = detial;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", gname=" + gname + ", gprice=" + gprice + ", credit=" + credit + ", image="
				+ image + ", detial=" + detial + "]";
	}
	
}

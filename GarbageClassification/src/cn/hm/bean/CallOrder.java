package cn.hm.bean;

import java.util.Date;
import java.util.List;

public class CallOrder {

	private String oid;
	private Date ordertime;
	private int uid;
	private double ctotal;
	private int state; // 订单状态： 1.待确认 2.待受理3.已受理4.订单完成
	private List<CallOrderItem> corderitemlist;
	private String oname;
	private String otel;
	private Date gotime; // 上门服务时间
	private String address;
	private String remark;

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public Date getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public double getCtotal() {
		return ctotal;
	}

	public void setCtotal(double ctotal) {
		this.ctotal = ctotal;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public List<CallOrderItem> getCorderitemlist() {
		return corderitemlist;
	}

	public void setCorderitemlist(List<CallOrderItem> corderitemlist) {
		this.corderitemlist = corderitemlist;
	}

	public String getOname() {
		return oname;
	}

	public void setOname(String oname) {
		this.oname = oname;
	}

	public String getOtel() {
		return otel;
	}

	public void setOtel(String otel) {
		this.otel = otel;
	}

	public Date getGotime() {
		return gotime;
	}

	public void setGotime(Date gotime) {
		this.gotime = gotime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "CallOrder [oid=" + oid + ", ordertime=" + ordertime + ", uid=" + uid + ", ctotal=" + ctotal + ", state="
				+ state + ", corderitemlist=" + corderitemlist + ", oname=" + oname + ", otel=" + otel + ", gotime="
				+ gotime + ", address=" + address + ", remark=" + remark + "]";
	}

}

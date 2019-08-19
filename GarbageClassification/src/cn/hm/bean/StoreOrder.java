package cn.hm.bean;

import java.util.Date;
import java.util.List;

public class StoreOrder {
	private String oid;
	private Date ordertime;
	private double ototal;
	private int ostate;
	private int uid;
	private String address;
	private String deaddress;
	private String phone;
	private List<StoreOrderItem> list;

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

	public double getOtotal() {
		return ototal;
	}

	public void setOtotal(double ototal) {
		this.ototal = ototal;
	}

	public int getOstate() {
		return ostate;
	}

	public void setOstate(int ostate) {
		this.ostate = ostate;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<StoreOrderItem> getList() {
		return list;
	}

	public void setList(List<StoreOrderItem> list) {
		this.list = list;
	}

	public String getDeaddress() {
		return deaddress;
	}

	public void setDeaddress(String deaddress) {
		this.deaddress = deaddress;
	}
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "StoreOrder [oid=" + oid + ", ordertime=" + ordertime + ", ototal=" + ototal + ", ostate=" + ostate
				+ ", uid=" + uid + ", address=" + address + ", deaddress=" + deaddress + ", list=" + list + "]";
	}

}

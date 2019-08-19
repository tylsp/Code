package cn.hm.bean;


import java.util.Date;

public class Donor {
	private int did;
	private double money;
	private Date donatetime;
	private int uid;
	private int cid;
	private User user;
	private CommonGood commongood;
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getDid() {
		return did;
	}
	public void setDid(int did) {
		this.did = did;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	
	public Date getDonatetime() {
		return donatetime;
	}
	public void setDonatetime(Date donatetime) {
		this.donatetime = donatetime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public CommonGood getCommongood() {
		return commongood;
	}
	public void setCommongood(CommonGood commongood) {
		this.commongood = commongood;
	}
	@Override
	public String toString() {
		return "Donor [did=" + did + ", money=" + money + ", donatetime=" + donatetime + ", user=" + user
				+ ", commongood=" + commongood + "]";
	}
	
	
}

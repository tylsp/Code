package cn.hm.bean;

import java.util.List;

public class CommonGood {
	private int cid;//项目id
	private String cname;//项目名称
	private String cimage;//项目图片
	private String summary;//项目简介
	private String ccontent;//项目详情内容
	private double ctotal;//项目获得的总捐款
	private List<Donor> donorlist;//此捐款项目下所有捐款单
	
	
	public double getCtotal() {
		return ctotal;
	}
	/**
	 * 设置该捐款项目下的 捐的钱的总数
	 * @param donorlist
	 */
	public void setCtotal(List<Donor> donorlist) {
		double ctotal = 0;
		for(Donor donor:donorlist) {
			ctotal+=donor.getMoney();
		}
		this.ctotal = ctotal;
	}
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
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public List<Donor> getDonorlist() {
		return donorlist;
	}
	public void setDonorlist(List<Donor> donorlist) {
		this.donorlist = donorlist;
	}
	@Override
	public String toString() {
		return "CommonGood [cid=" + cid + ", cname=" + cname + ", cimage=" + cimage + ", summary=" + summary
				+ ", ccontent=" + ccontent + ", ctotal=" + ctotal + ", donorlist=" + donorlist + "]";
	}

	
}

package cn.hm.bean;

import java.util.Date;

public class Comment {
	private int cid;	//帖子id
	private int pid;	//对应的帖子id
	private int uid;	//回帖用户
	private Date time;      //回帖时间
	private String comment;   //回帖内容
	private User user;//回帖的用户对象

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Comment(int cid, int pid, int uid, Date time, String comment) {
		super();
		this.cid = cid;
		this.pid = pid;
		this.uid = uid;
		this.time = time;
		this.comment = comment;
	}
	public Comment() {
		super();
	}
	@Override
	public String toString() {
		return "Comment [cid=" + cid + ", pid=" + pid + ", uid=" + uid + ", time=" + time + ", comment=" + comment
				+ "]";
	}
	
}

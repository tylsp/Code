package cn.hm.bean;

import java.util.Date;

/**
 * 对应帖子表
 * @author Administrator
 *
 */
public class Posting {
    private int pid;      //帖子id
    private int tid;       //帖子主题(外键)
    private int uid;       //发帖用户id(外键)
    private String title;    //帖子标题
    private String content;   //发帖内容
    private Date time;      //发帖时间
    private int status;		//帖子状态,0 待审核,1 审核成功,2审核失败
    private User user;    //发帖的用户
    private Topic topic;  //发帖的主题
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	public Posting(int pid, int tid, int uid, String title, String content, Date time, User user, Topic topic) {
		super();
		this.pid = pid;
		this.tid = tid;
		this.uid = uid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.user = user;
		this.topic = topic;
	}
	public Posting() {
		super();
	}
	@Override
	public String toString() {
		return "Posting [pid=" + pid + ", tid=" + tid + ", uid=" + uid + ", title=" + title + ", content=" + content
				+ ", time=" + time + ", user=" + user + ", topic=" + topic + "]";
	}
    
    
}

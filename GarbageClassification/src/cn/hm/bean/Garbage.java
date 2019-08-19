package cn.hm.bean;

public class Garbage {
	private int gid;//垃圾id 主键
	private String name;//垃圾名称
	private int tid;//垃圾对应的类别id
	private String cname;//垃圾对应的类别名称
	
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public Garbage(int gid, String name, int tid) {
		super();
		this.gid = gid;
		this.name = name;
		this.tid = tid;
	}
	public Garbage() {
		super();
	}
	@Override
	public String toString() {
		return "Garbage [gid=" + gid + ", name=" + name + ", tid=" + tid + "]";
	}
	
}

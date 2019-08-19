package cn.hm.bean;

public class GarbageType {
	private int tid;//垃圾类别id
	private String name;//类别名称
	private String image;//类别图片
	private String define;//垃圾类别定义
	private String require;//类别投放要求
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDefine() {
		return define;
	}
	public void setDefine(String define) {
		this.define = define;
	}
	public String getRequire() {
		return require;
	}
	public void setRequire(String require) {
		this.require = require;
	}
	public GarbageType(int tid, String name, String image, String define, String require) {
		super();
		this.tid = tid;
		this.name = name;
		this.image = image;
		this.define = define;
		this.require = require;
	}
	public GarbageType() {
		super();
	}
	@Override
	public String toString() {
		return "GarbageType [tid=" + tid + ", name=" + name + ", image=" + image + ", define=" + define + ", require="
				+ require + "]";
	}
	
}

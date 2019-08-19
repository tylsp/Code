package cn.hm.service;

import java.util.Date;
import java.util.List;

import cn.hm.bean.CallOrder;
import cn.hm.bean.PageBean;

public interface CallOrderService {

	// 创建订单
	void createOrder(CallOrder co);

	// 获取订单
	List<CallOrder> getCallOrder(int uid, int state);

	// 修改订单状态
	boolean updateStatus(String oid, int status);

	// 撤销订单
	boolean cancelOrder(String oid);
	
	//设置callorderitem的iweight和subtotal字段
	boolean setProtities(String iid, double iweight, double subtotal);
	
	//设置 callorder的总钱数
	boolean setCTotal(String oid, double total);
	
	//订单确认提交后，将表单里的数据写入数据库
	boolean setFormProtities(String oid, String oname, String otel, Date gotime, String address, String remark);
	
	//获取订单+分页（后台用）
	PageBean<CallOrder> getOrderControl(int...value);
	
	//检查订单是否被受理
	boolean checkState(String oid);
	
	//根据iid去查cid
	int getCidByiid(String iid);

}

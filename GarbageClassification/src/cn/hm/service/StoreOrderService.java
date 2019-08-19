package cn.hm.service;


import java.util.Date;
import java.util.List;

import cn.hm.bean.Page;
import cn.hm.bean.StoreOrder;

public interface StoreOrderService {
	//修改订单状态
		boolean updateState(String oid,int ostate);
		//创建订单
		void createStoreOrder(StoreOrder order);
		//获取订单
		List<StoreOrder> getStoreOrder(int uid,int ostate);
		//获取订单
		StoreOrder getOrder(String oid);
		//删除订单条目
		boolean deleteOrderItem(String oid);
		//删除订单
		boolean deleteOrder(String oid);
		
		//修改订单时间
		boolean updateDate(Date date,String oid);
		//分页(用户)
		Page<StoreOrder> findAllOrderUser(int currentPageNo, int pageSize,int ostate,int uid);
		//分页(后台)
		Page<StoreOrder> findAllOrder(int currentPageNo, int pageSize,int ostate);
}

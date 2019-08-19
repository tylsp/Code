package cn.hm.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.hm.bean.Page;
import cn.hm.bean.StoreOrder;
import cn.hm.dao.StoreOrderDao;
import cn.hm.dao.impl.StoreOrderDaoImpl;
import cn.hm.service.StoreOrderService;
import cn.hm.util.JdbcUtil;

public class StoreOrderServiceImpl implements StoreOrderService {
	private StoreOrderDao dao = new StoreOrderDaoImpl();

	@Override
	public boolean updateState(String oid, int ostate) {
		// TODO Auto-generated method stub
		return dao.updateState(oid, ostate);
	}

	@Override
	public void createStoreOrder(StoreOrder order) {
		// TODO Auto-generated method stub
		dao.createStoreOrder(order);
		dao.createStoreOrderItem(order.getList());
	}

	@Override
	public List<StoreOrder> getStoreOrder(int uid,int ostate) {
		// TODO Auto-generated method stub
		return dao.getStoreOrder(uid,ostate);
	}

	@Override
	public StoreOrder getOrder(String oid) {
		// TODO Auto-generated method stub
		return dao.getOrder(oid);
	}

	@Override
	public boolean deleteOrderItem(String oid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOrder(String oid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		 try {
			 JdbcUtil.beginTransaction();
			 dao.deleteOrderItem(oid);
			 dao.deleteOrder(oid);
			 JdbcUtil.commitTransaction();
			 bool = true;
		 }catch(Exception e) {
			 try {
				JdbcUtil.rollbackTranaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 }
		
		return bool;
	}

	@Override
	public boolean updateDate(Date date, String oid) {
		// TODO Auto-generated method stub
		return dao.updateDate(date, oid);
	}

	@Override
	public Page<StoreOrder> findAllOrder(int currentPageNo, int pageSize,int ostate) {
		// TODO Auto-generated method stub
		return dao.findAllOrder(currentPageNo, pageSize,ostate);
	}

	@Override
	public Page<StoreOrder> findAllOrderUser(int currentPageNo, int pageSize, int ostate, int uid) {
		// TODO Auto-generated method stub
		return dao.findAllOrderUser(currentPageNo, pageSize, ostate, uid);
	}


}

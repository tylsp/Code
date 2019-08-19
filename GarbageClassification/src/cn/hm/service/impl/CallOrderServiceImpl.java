package cn.hm.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import cn.hm.bean.CallOrder;
import cn.hm.bean.PageBean;
import cn.hm.dao.CallOrderDao;
import cn.hm.dao.impl.CallOrderDaoImpl;
import cn.hm.service.CallOrderService;
import cn.hm.util.JdbcUtil;

public class CallOrderServiceImpl implements CallOrderService {
	
	private CallOrderDao cod = new CallOrderDaoImpl();

	@Override
	public void createOrder(CallOrder co) {
		// TODO Auto-generated method stub
		try {
			JdbcUtil.beginTransaction();
			cod.createOrder(co);
			cod.addCallOrderItems(co.getCorderitemlist());
			JdbcUtil.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				JdbcUtil.rollbackTranaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public List<CallOrder> getCallOrder(int uid, int state) {
		// TODO Auto-generated method stub
		return cod.getCallOrder(uid,state);
	}

	@Override
	public boolean updateStatus(String oid, int status) {
		// TODO Auto-generated method stub
		return cod.updateStatus(oid, status);
	}

	@Override
	public boolean cancelOrder(String oid) {
		// TODO Auto-generated method stub
		return cod.cancelOrder(oid);
	}

	@Override
	public boolean setProtities(String iid, double iweight, double subtotal) {
		// TODO Auto-generated method stub
		return cod.setProtities(iid, iweight, subtotal);
	}

	@Override
	public boolean setCTotal(String oid, double total) {
		// TODO Auto-generated method stub
		return cod.setCTotal(oid, total);
	}

	@Override
	public boolean setFormProtities(String oid, String oname, String otel, Date gotime, String address, String remark) {
		// TODO Auto-generated method stub
		return cod.setFormProtities(oid, oname, otel, gotime, address, remark);
	}

	@Override
	public PageBean<CallOrder> getOrderControl(int... value) {
		// TODO Auto-generated method stub
		return cod.getOrderControl(value);
	}

	@Override
	public boolean checkState(String oid) {
		// TODO Auto-generated method stub
		return cod.checkState(oid);
	}

	@Override
	public int getCidByiid(String iid) {
		// TODO Auto-generated method stub
		return cod.getCidByiid(iid);
	}

}

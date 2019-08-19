package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.CallItem;
import cn.hm.dao.CallItemDao;
import cn.hm.dao.impl.CallItemDaoImpl;
import cn.hm.service.CallItemService;

public class CallItemServiceImpl implements CallItemService {
	
	private CallItemDao cd = new CallItemDaoImpl();

	@Override
	public List<CallItem> getAllCallItems() {
		// TODO Auto-generated method stub
		return cd.getAllCallItems();
	}

	@Override
	public List<Integer> getAllCid() {
		// TODO Auto-generated method stub
		return cd.getAllCid();
	}

	@Override
	public CallItem getCallItemByCid(int cid) {
		// TODO Auto-generated method stub
		return cd.getCallItemByCid(cid);
	}

	@Override
	public boolean deleteIidByOid(String oid) {
		// TODO Auto-generated method stub
		return cd.deleteIidByOid(oid);
	}

	@Override
	public double searchPriceByiid(int cid) {
		// TODO Auto-generated method stub
		return cd.searchPriceByiid(cid);
	}

}

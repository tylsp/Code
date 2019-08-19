package cn.hm.dao;

import java.util.List;

import cn.hm.bean.CallItem;

public interface CallItemDao {
	
	List<CallItem> getAllCallItems();
	
	List<Integer> getAllCid();
	
	CallItem getCallItemByCid(int cid);
	
	//清除对应oid的iid
	boolean deleteIidByOid(String oid);
	
	//根据iid查询price
	double searchPriceByiid(int cid);

}

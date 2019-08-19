package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.GarbageType;
import cn.hm.dao.GarbageTypeDao;
import cn.hm.dao.impl.GarbageTypeDaoImpl;
import cn.hm.service.GarbageTypeService;

public class GarbageTypeServiceImpl implements GarbageTypeService {

	private GarbageTypeDao dao = new GarbageTypeDaoImpl();
	@Override
	public List<GarbageType> getAllType() {
		return dao.getAllType();
	}
	@Override
	public GarbageType getTypeByTid(int tid) {
		// TODO Auto-generated method stub
		return dao.getTypeByTid(tid);
	}

}

package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Garbage;
import cn.hm.dao.GarbageDao;
import cn.hm.dao.impl.GarbageDaoImpl;
import cn.hm.service.GarbageService;

public class GarbageServiceImpl implements GarbageService {

	private GarbageDao dao = new GarbageDaoImpl();
	@Override
	public List<Garbage> selectByName(String name) {
		// TODO Auto-generated method stub
		return dao.selectByName(name);
	}

	@Override
	public List<Garbage> selctByType(int tid) {
		// TODO Auto-generated method stub
		return dao.selctByType(tid);
	}

}

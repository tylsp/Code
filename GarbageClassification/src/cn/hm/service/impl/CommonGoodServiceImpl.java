package cn.hm.service.impl;

import java.util.List;


import cn.hm.bean.CommonGood;
import cn.hm.dao.CommonGoodDao;
import cn.hm.dao.impl.CommonGoodDaoImpl;
import cn.hm.service.CommonGoodService;

public class CommonGoodServiceImpl implements CommonGoodService {

	private CommonGoodDao dao = new CommonGoodDaoImpl();
	@Override
	public List<CommonGood> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	@Override
	public CommonGood selectByCid(int cid) {
		// TODO Auto-generated method stub
		return dao.selectByCid(cid);
	}
	

}

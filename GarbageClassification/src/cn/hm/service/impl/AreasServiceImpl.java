package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Areas;
import cn.hm.dao.AreasDao;
import cn.hm.dao.impl.AreasDaoImpl;
import cn.hm.service.AreasService;

public class AreasServiceImpl implements AreasService {
	private AreasDao dao = new AreasDaoImpl();
	@Override
	public List<Areas> selectArea(String cityid) {
		// TODO Auto-generated method stub
		return dao.selectArea(cityid);
	}

}

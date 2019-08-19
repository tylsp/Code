package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Provinces;
import cn.hm.dao.ProvincesDao;
import cn.hm.dao.impl.ProvincesDaoImpl;
import cn.hm.service.ProvincesService;

public class ProvincesServiceImpl implements ProvincesService {
	private ProvincesDao dao = new ProvincesDaoImpl();
	@Override
	public List<Provinces> selectProvice() {
		// TODO Auto-generated method stub
		return dao.selectProvice();
	}

	@Override
	public Provinces selectPro(String province) {
		// TODO Auto-generated method stub
		return dao.selectPro(province);
	}

}

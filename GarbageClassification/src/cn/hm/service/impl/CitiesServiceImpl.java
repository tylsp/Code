package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Cities;
import cn.hm.dao.CitiesDao;
import cn.hm.dao.impl.CitiesDaoImpl;
import cn.hm.service.CitiesService;

public class CitiesServiceImpl implements CitiesService {
	private CitiesDao dao = new CitiesDaoImpl();
	@Override
	public List<Cities> selectCity(String provinceid) {
		// TODO Auto-generated method stub
		return dao.selectCity(provinceid);
	}
	@Override
	public Cities selectCi(String city) {
		// TODO Auto-generated method stub
		return dao.selectCi(city);
	}

}

package cn.hm.service;

import java.util.List;

import cn.hm.bean.Cities;

public interface CitiesService {
	List<Cities> selectCity(String provinceid);
	Cities selectCi(String city);
}

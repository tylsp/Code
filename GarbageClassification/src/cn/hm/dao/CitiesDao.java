package cn.hm.dao;

import java.util.List;

import cn.hm.bean.Cities;



public interface CitiesDao {
	List<Cities> selectCity(String provinceid);
	Cities selectCi(String city);
}

package cn.hm.dao;

import java.util.List;

import cn.hm.bean.Provinces;



public interface ProvincesDao {
	List<Provinces> selectProvice();

	Provinces selectPro(String province);
}

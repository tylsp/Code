package cn.hm.service;

import java.util.List;

import cn.hm.bean.Provinces;

public interface ProvincesService {
	List<Provinces> selectProvice();

	Provinces selectPro(String province);
}

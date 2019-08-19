package cn.hm.service;

import java.util.List;


import cn.hm.bean.CommonGood;


public interface CommonGoodService {
	 List<CommonGood> findAll();
	 
	 CommonGood selectByCid(int cid);
}

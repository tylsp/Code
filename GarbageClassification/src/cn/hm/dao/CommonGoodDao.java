package cn.hm.dao;

import java.util.List;


import cn.hm.bean.CommonGood;

public interface CommonGoodDao {

	/**
	 * 查询所有公益项目
	 * @return
	 */
	List<CommonGood> findAll();

	CommonGood selectByCid(int cid);
	
}

package cn.hm.dao;

import java.util.List;

import cn.hm.bean.Goods;

public interface GoodsDao {
	//查询所有商品
	List<Goods> getAllGoods();
	//根据id查询商品
	Goods getGoodsById(int gid);
}

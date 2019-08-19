package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Goods;
import cn.hm.dao.GoodsDao;
import cn.hm.dao.impl.GoodsDaoImpl;
import cn.hm.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDao dao = new GoodsDaoImpl();
	@Override
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		return dao.getAllGoods();
	}
	@Override
	public Goods getGoodsById(int gid) {
		// TODO Auto-generated method stub
		return dao.getGoodsById(gid);
	}

}

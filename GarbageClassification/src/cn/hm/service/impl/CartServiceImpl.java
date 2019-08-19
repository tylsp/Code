package cn.hm.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.hm.bean.Cart;
import cn.hm.bean.CartItem;
import cn.hm.dao.CartDao;
import cn.hm.dao.CartItemDao;
import cn.hm.dao.impl.CartDaoImpl;
import cn.hm.dao.impl.CartItemDaoImpl;
import cn.hm.service.CartService;
import cn.hm.util.JdbcUtil;

public class CartServiceImpl implements CartService {
	private CartDao dao = new CartDaoImpl();
	private CartItemDao daoitem = new CartItemDaoImpl();

	@Override
	public boolean isExistCart(int uid) {
		// TODO Auto-generated method stub
		return dao.isExistCart(uid);
	}

	@Override
	public Cart getCart(int uid) {
		// TODO Auto-generated method stub
		return dao.getCart(uid);
	}

	@Override
	public boolean createCart(double total, int uid) {
		// TODO Auto-generated method stub
		return dao.createCart(total, uid);
	}

	

	@Override
	public Cart getMyCart(int uid) {
		Cart ca = null;
		boolean isExist = dao.isExistCart(uid);
		if(!isExist) {
			double total = 0;
			dao.createCart(total, uid);
		}
		ca = dao.getCart(uid);
		return ca;
		
	}
	
	public Cart getMapData(Cart cart,List<CartItem> list) {
		for(CartItem item:list) {
			cart.getMap().put(item.getGoods().getGid(),item);
		}
		return cart;
	}

	@Override
	public boolean updateTotal(int cartId, double total) throws SQLException {
		// TODO Auto-generated method stub
		return dao.updateTotal(cartId, total);
	}

	@Override
	public void clearMyCart(int cartId) {
		try {
			JdbcUtil.beginTransaction();
			daoitem.deleteAllItem(cartId);
			updateTotal(cartId, 0);
			JdbcUtil.commitTransaction();
		}catch(Exception e) {
			try {
				JdbcUtil.rollbackTranaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

}

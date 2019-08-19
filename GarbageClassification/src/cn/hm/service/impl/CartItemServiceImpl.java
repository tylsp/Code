package cn.hm.service.impl;

import java.sql.SQLException;
import java.util.List;

import cn.hm.bean.CartItem;
import cn.hm.dao.CartDao;
import cn.hm.dao.CartItemDao;
import cn.hm.dao.impl.CartDaoImpl;
import cn.hm.dao.impl.CartItemDaoImpl;
import cn.hm.service.CartItemService;
import cn.hm.service.CartService;
import cn.hm.util.JdbcUtil;

public class CartItemServiceImpl implements CartItemService {
	private CartItemDao dao = new CartItemDaoImpl();
	private CartService server = new CartServiceImpl();
	private CartDao daocart = new CartDaoImpl();

	@Override
	public boolean addItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		return dao.addItem(cartItem);
	}

	@Override
	public boolean deleteItem(int cartId,int gid) {
		// TODO Auto-generated method stub
		return dao.deleteItem(cartId, gid);
	}

	@Override
	public boolean updateCount(int cid, int count) {
		// TODO Auto-generated method stub
		return dao.updateCount(cid, count);
	}

	@Override
	public boolean updateCtotal(int cid, double total) {
		// TODO Auto-generated method stub
		return dao.updateCtotal(cid, total);
	}

	@Override
	public List<CartItem> findItem(int cartId) {
		// TODO Auto-generated method stub
		return dao.findItem(cartId);
	}

	@Override
	public CartItem finItemById(int cartId, int gid) {
		// TODO Auto-generated method stub
		return dao.finItemById(cartId, gid);
	}

	@Override
	public boolean updateAll(int cartId,CartItem cartItem,int count) {
		boolean bool = false;
		CartItem ci = dao.finItemById(cartId, cartItem.getGoods().getGid());
		try {
			JdbcUtil.beginTransaction();
			if(ci != null) {
				int temp = ci.getCount();
				if(temp+count<=0) {
					dao.deleteItem(cartId, cartItem.getGoods().getGid());
				}
				bool = true;
			}else {
				if(count <= 0) {
					server.updateTotal(cartId, 0);
					return bool;
				}
				dao.addItem(cartItem);
				ci = dao.finItemById(cartId, cartItem.getGoods().getGid());
				bool = true;
				
			}
			dao.updateCount(ci.getCid(), count);			
			dao.updateCtotal(ci.getCid(), cartItem.getGoods().getGprice() * count);
			double sum = getTotal(cartId);
			server.updateTotal(cartId, sum);
			JdbcUtil.commitTransaction();
		}catch(Exception e) {
			try {
				JdbcUtil.rollbackTranaction();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return bool;
	}

	@Override
	public boolean deleteAllItem(int cartId) throws SQLException {
		// TODO Auto-generated method stub
		return dao.deleteAllItem(cartId);
	}

	@Override
	public CartItem getItemByGid(int gid) {
		// TODO Auto-generated method stub
		return dao.getItemByGid(gid);
	}

	@Override
	public double getTotal(int cartId) {
		// TODO Auto-generated method stub
		return dao.getTotal(cartId);
	} 

}

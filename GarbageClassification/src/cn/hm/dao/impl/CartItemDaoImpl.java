package cn.hm.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hm.bean.CartItem;
import cn.hm.bean.Goods;
import cn.hm.dao.CartItemDao;
import cn.hm.util.JdbcUtil;

public class CartItemDaoImpl implements CartItemDao {
	private QueryRunner qr = new QueryRunner();
	@Override
	public boolean addItem(CartItem cartItem) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "insert into `cartitem` values (null,?,?,?,?)";
		try {
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql,cartItem.getCartid(),cartItem.getCount(),cartItem.getCtotal(),cartItem.getGoods().getGid());
			if(line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public boolean deleteItem(int cartId,int gid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "delete from `cartitem` where `cartId` = ? and `gid` = ?";
		try {
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql, cartId,gid);
			if(line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public boolean updateCount(int cid,int count) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "update `cartitem` set `count` = `count` + ? where `cid` = ?";
		try {
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql, count,cid);
			if(line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public boolean updateCtotal(int cid,double total) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "update `cartitem` set `ctotal` = `ctotal` + ? where `cid` = ?";
		try {
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql, total,cid);
			if(line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public List<CartItem> findItem(int cartId) {
		// TODO Auto-generated method stub
		List<CartItem> list = new ArrayList<CartItem>();
		List<Map<String,Object>> maps = null;
		Connection con = null;
		String sql = "select * from `cartitem` a,`goods` b where a.gid = b.gid and `cartid` = ?";
		try {
			con = JdbcUtil.getConnection();
//			list = qr.query(con, sql, new BeanListHandler<CartItem>(CartItem.class), cartId);
			sql = "select * from `cartitem` a,`goods` b where a.gid = b.gid and `cartid` = ?";
			maps = qr.query(con, sql, new MapListHandler(), cartId);
			for(Map<String,Object> map:maps ) {
				CartItem item = new CartItem();
				Goods goods = new Goods();
				try {
					BeanUtils.populate(item, map);
					BeanUtils.populate(goods, map);
					item.setGoods(goods);
					list.add(item);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public CartItem finItemById(int cartId, int gid) {
		// TODO Auto-generated method stub
		CartItem item = null;
		Connection con = null;
		String sql = "select * from `cartitem` where `cartid` = ? and `gid` = ?";
		try {
			con = JdbcUtil.getConnection();
			item = qr.query(con, sql, new BeanHandler<CartItem>(CartItem.class), cartId,gid);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return item;
	}

	@Override
	public double getTotal(int cartId) {
		// TODO Auto-generated method stub
		double sum = 0;
		CartItem item = new CartItem();
		Connection con = null;
		String sql = "select * from `cartitem` where `cartid` = ?";
		try {
			con = JdbcUtil.getConnection();
			item = qr.query(con, sql, new BeanHandler<>(CartItem.class), cartId);
			if(item == null) {
				return sum;
			}
			sql = "select sum(`ctotal`) from `cartitem` where `cartid` = ?";
			sum = qr.query(con, sql, new ScalarHandler<Double>(), cartId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return sum;
	}

	@Override
	public boolean deleteAllItem(int cartId) throws SQLException {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "delete from `cartitem` where `cartid` = ?";
		
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql,cartId);
			if(line > 0) {
				bool = true;
			}
		
		return bool;
	}

	@Override
	public CartItem getItemByGid(int gid) {
		// TODO Auto-generated method stub
		CartItem item = null;
		Connection con = null;
		String sql = "select * from `cartitem` where `gid` = ?";
		try {
			con = JdbcUtil.getConnection();
			item = qr.query(con, sql, new BeanHandler<>(CartItem.class), gid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return item;
	}

}

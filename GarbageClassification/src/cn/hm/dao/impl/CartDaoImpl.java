package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.hm.bean.Cart;
import cn.hm.dao.CartDao;
import cn.hm.util.JdbcUtil;

public class CartDaoImpl implements CartDao {
	private QueryRunner qr = new QueryRunner();
	@Override
	public boolean isExistCart(int uid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		Cart cart = null;
		String sql = "select * from `cart` where `uid` = ?";
		try {
			con = JdbcUtil.getConnection();
			cart = qr.query(con, sql, new BeanHandler<Cart>(Cart.class), uid);
			if(cart != null) {
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
	public Cart getCart(int uid) {
		// TODO Auto-generated method stub
		Connection con = null;
		Cart cart = null;
		String sql = "select * from `cart` where `uid` = ?";
		try {
			con = JdbcUtil.getConnection();
			cart = qr.query(con, sql, new BeanHandler<Cart>(Cart.class), uid);
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
		return cart;
	}

	@Override
	public boolean createCart(double total,int uid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "insert into `cart` values (null,?,?)";
		try {
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql, uid,total);
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
	public boolean updateTotal(int cartId,double total) throws SQLException {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "update `cart` set `total` = ? where `cartId` = ?";
	
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql, total,cartId);
			if(line > 0) {
				bool = true;
			}
		
		
		return bool;
	}


}

package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hm.bean.CallItem;
import cn.hm.dao.CallItemDao;
import cn.hm.util.JdbcUtil;

public class CallItemDaoImpl implements CallItemDao {

	QueryRunner qr = new QueryRunner();

	@Override
	public List<CallItem> getAllCallItems() {
		// TODO Auto-generated method stub
		List<CallItem> list = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `callitem`";
			list = qr.query(conn, sql, new BeanListHandler<CallItem>(CallItem.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<Integer> getAllCid() {
		// TODO Auto-generated method stub
		List<Integer> list = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "SELECT cid FROM `callitem`";
			list = qr.query(conn, sql, new ColumnListHandler<Integer>());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public CallItem getCallItemByCid(int cid) {
		// TODO Auto-generated method stub
		CallItem callItem = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `callitem` WHERE cid = ?";
			callItem = (CallItem) qr.query(conn, sql, new BeanHandler<CallItem>(CallItem.class),cid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return callItem;
	}

	@Override
	public boolean deleteIidByOid(String oid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "DELETE FROM `callorderitem` WHERE oid = ?";
			int len = qr.update(conn, sql, oid);
			if(len > 0){
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public double searchPriceByiid(int cid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		double price = 0.0;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "SELECT cprice FROM callitem WHERE cid = ?";
			Number money = qr.query(conn, sql, new ScalarHandler<Number>(), cid);
			price = money.doubleValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return price;
	}

}

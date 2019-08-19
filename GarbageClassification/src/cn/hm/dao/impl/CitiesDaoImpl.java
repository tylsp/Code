package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Cities;
import cn.hm.dao.CitiesDao;
import cn.hm.util.JdbcUtil;

public class CitiesDaoImpl implements CitiesDao {
	private QueryRunner qr = new QueryRunner();
	@Override
	public List<Cities> selectCity(String provinceid) {
		// TODO Auto-generated method stub
		List<Cities> list = null;
		Connection con = null;
		String sql = "select * from `cities` where `provinceid` = ?";
		try {
			con = JdbcUtil.getConnection();
			list = qr.query(con, sql, new BeanListHandler<Cities>(Cities.class), provinceid);
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
	public Cities selectCi(String city) {
		// TODO Auto-generated method stub
		Cities cities = null;
		Connection con = null;
		String sql = "select * from `cities` where `city` = ?";
		try {
			con = JdbcUtil.getConnection();
			cities = qr.query(con, sql, new BeanHandler<Cities>(Cities.class), city);
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
		return cities;
	}

}

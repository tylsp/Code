package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Provinces;
import cn.hm.dao.ProvincesDao;
import cn.hm.util.JdbcUtil;

public class ProvincesDaoImpl implements ProvincesDao {
	private QueryRunner qr = new QueryRunner();
	@Override
	public List<Provinces> selectProvice() {
		List<Provinces> list = null;
		Connection con = null;
		String sql = "select * from `provinces`";
		try {
			con = JdbcUtil.getConnection();
			list = qr.query(con, sql, new BeanListHandler<Provinces>(Provinces.class));
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
	public Provinces selectPro(String province) {
		// TODO Auto-generated method stub
		Provinces provinces = new Provinces();
		Connection con = null;
		String sql = "select * from `provinces` where `province` = ?";
		try {
			con = JdbcUtil.getConnection();
			provinces = qr.query(con, sql, new BeanHandler<Provinces>(Provinces.class), province);
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
		return provinces;
	}

}

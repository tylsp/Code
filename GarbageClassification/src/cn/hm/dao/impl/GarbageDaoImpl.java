package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Garbage;
import cn.hm.dao.GarbageDao;
import cn.hm.util.JdbcUtil;

public class GarbageDaoImpl implements GarbageDao {
	private QueryRunner qr = new QueryRunner();

	/**
	 * 按垃圾名称模糊查询
	 */
	@Override
	public List<Garbage> selectByName(String name) {
		Connection con = null;
		List<Garbage> garbageList = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `garbage` where name like ?";
			garbageList = qr.query(con, sql, new BeanListHandler<Garbage>(Garbage.class),"%"+name+"%");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return garbageList;
	}

	/**
	 * 按垃圾类别显示垃圾
	 */
	@Override
	public List<Garbage> selctByType(int tid) {
		Connection con = null;
		List<Garbage> garbageList = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `garbage` where tid = ?";
			garbageList = qr.query(con, sql, new BeanListHandler<Garbage>(Garbage.class),tid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con!=null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return garbageList;
	}

}

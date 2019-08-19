package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.GarbageType;
import cn.hm.dao.GarbageTypeDao;
import cn.hm.util.JdbcUtil;

public class GarbageTypeDaoImpl implements GarbageTypeDao {

	private QueryRunner qr = new QueryRunner();
	/**
	 * 选择所有分类
	 */
	@Override
	public List<GarbageType> getAllType() {
		Connection con = null;
		List<GarbageType> typeList = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `garbagetype`";
			typeList = qr.query(con, sql, new BeanListHandler<GarbageType>(GarbageType.class));
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
		return typeList;
	}
	
	/**
	 * 根据tid得到垃圾类别
	 */
	@Override
	public GarbageType getTypeByTid(int tid) {
		Connection con = null;
		GarbageType type = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `garbagetype` WHERE `tid` = ?";
			type = qr.query(con, sql, new BeanHandler<GarbageType>(GarbageType.class),tid);
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
		return type;
	}
	

}

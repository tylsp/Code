package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Areas;
import cn.hm.dao.AreasDao;
import cn.hm.util.JdbcUtil;

public class AreasDaoImpl implements AreasDao {
	private QueryRunner qr = new QueryRunner();
	@Override
	public List<Areas> selectArea(String cityid) {
		// TODO Auto-generated method stub
		List<Areas> list = null;
		Connection con = null;
		String sql = "select * from `areas` where `cityid` = ?";
		try {
			con = JdbcUtil.getConnection();
			list = qr.query(con, sql, new BeanListHandler<Areas>(Areas.class), cityid);
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

}

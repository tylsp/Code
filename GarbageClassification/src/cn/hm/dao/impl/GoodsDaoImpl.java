package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Goods;
import cn.hm.dao.GoodsDao;
import cn.hm.util.JdbcUtil;

public class GoodsDaoImpl implements GoodsDao {
	private QueryRunner qr = new QueryRunner();
	@Override
	public List<Goods> getAllGoods() {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Goods> list = null;
		String sql = "select * from `goods`";
		try {
			con = JdbcUtil.getConnection();
			list = qr.query(con, sql, new BeanListHandler<Goods>(Goods.class));
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
	public Goods getGoodsById(int gid) {
		// TODO Auto-generated method stub
		Goods goods = null;
		Connection con = null;
		String sql = "select * from `goods` where `gid` = ?";
		try {
			con = JdbcUtil.getConnection();
			goods = qr.query(con, sql, new BeanHandler<Goods>(Goods.class), gid);
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
		return goods;
	}

}

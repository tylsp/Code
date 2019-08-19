package cn.hm.dao.impl;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.CommonGood;
import cn.hm.bean.Donor;
import cn.hm.dao.CommonGoodDao;
import cn.hm.util.JdbcUtil;

public class CommonGoodDaoImpl implements CommonGoodDao {
	private QueryRunner qr = new QueryRunner();
	
	@Override
	public List<CommonGood> findAll() {
		List<CommonGood> commonlist = null;
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from commongood";
			commonlist = qr.query(con, sql, new BeanListHandler<CommonGood>(CommonGood.class));
			for(int i=0;i<commonlist.size();i++) {
				CommonGood commongood = commonlist.get(i);
				List<Donor> donorlist = getDonorList(commongood);
				commongood.setDonorlist(donorlist);
			}
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
		return commonlist;
	}

	/**
	 * 获取指定捐款项目下的捐款单的集合
	 * @param commongood
	 * @return
	 */
	private  List<Donor> getDonorList(CommonGood commongood) {
		// TODO Auto-generated method stub
		Connection con = null;
		List<Donor> donorlist = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from donor where cid = ?";
			donorlist = qr.query(con, sql, new BeanListHandler<Donor>(Donor.class),commongood.getCid());
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
		return donorlist;
	}

	@Override
	public CommonGood selectByCid(int cid) {
		Connection con = null;
		CommonGood commongood = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "select * from commongood where cid = ?";
			commongood= qr.query(con, sql, new BeanHandler<CommonGood>(CommonGood.class),cid);
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
		return commongood;
	}

}

package cn.hm.dao.impl;





import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;


import cn.hm.bean.Donor;
import cn.hm.bean.User;
import cn.hm.dao.DonorDao;
import cn.hm.util.JdbcUtil;

public class DonorDaoImpl implements DonorDao {

	private QueryRunner qr = new QueryRunner();
	UserDaoImpl dao = new UserDaoImpl();
	/**
	 * 添加捐款单
	 */
	@Override
	public boolean addDonor(Donor donor) {
		boolean bool = false;
		Connection con = null;
		String sql = "insert into donor values(?,?,?,?,?)";
		try {
			con = JdbcUtil.getConnection();
			Timestamp time = new Timestamp(donor.getDonatetime().getTime());
			Object params[] = {donor.getDid(),donor.getMoney(),time,donor.getUid(),donor.getCid()};
			int num = qr.update(con, sql, params);
			if(num > 0) {
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

	/**
	 * 获得所有的捐款单，并将user放入捐款单对象donor中
	 */
	@Override
	public List<Donor> getAllDonor() {
		Connection con = null;
		List<Donor> donorlist = null;
		//联合查询
		String sql = "SELECT * FROM `donor`" ;
		try {
			con = JdbcUtil.getConnection();
			donorlist = qr.query(con, sql, new BeanListHandler<Donor>(Donor.class)) ;
			for(int i=0;i<donorlist.size();i++) {
				Donor donor = donorlist.get(i);
				User user = dao.getUserByUid(donor.getUid());
				donor.setUser(user);		
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
		return donorlist;
	}

	@Override
	public List<Donor> getDonorByUid(int uid) {
		Connection con = null;
		List<Donor> donorlist = null;
		String sql = "SELECT * FROM `donor` WHERE `uid` = ?" ;
		try {
			con = JdbcUtil.getConnection();
			donorlist = qr.query(con, sql, new BeanListHandler<Donor>(Donor.class),uid) ;
			for(int i=0;i<donorlist.size();i++) {
				Donor donor = donorlist.get(i);
				User user = dao.getUserByUid(donor.getUid());
				donor.setUser(user);
				
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
		return donorlist;
	}

}

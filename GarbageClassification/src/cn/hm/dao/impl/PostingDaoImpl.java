package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Posting;
import cn.hm.dao.PostingDao;
import cn.hm.util.JdbcUtil;

public class PostingDaoImpl implements PostingDao {
	
	QueryRunner qr = new QueryRunner();
	/**
	 * 查询所有的帖子
	 * @return
	 */
	@Override
	public List<Posting> getAllPosting() {
		List<Posting> postingList = null;
		Connection con = null;
		String sql = "SELECT * FROM `posting` WHERE `status` = 1";
		try {
			con = JdbcUtil.getConnection();
			postingList = qr.query(con, sql, new BeanListHandler<Posting>(Posting.class));
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
		return postingList;
	}

	/**
	 * 根据帖子主题id查询帖子
	 * @param tid
	 * @return
	 */
	@Override
	public List<Posting> getPostingByTid(int tid) {
		List<Posting> postingList = null;
		Connection con = null;
		String sql = "SELECT * FROM `posting` WHERE `tid` = ? AND `status` = 1";
		try {
			con = JdbcUtil.getConnection();
			postingList = qr.query(con, sql, new BeanListHandler<Posting>(Posting.class),tid);
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
		return postingList;
	}

	/**
	 * 插入帖子到数据库
	 * @param posting
	 * @return
	 */
	@Override
	public boolean insertPosting(Posting posting) {
		boolean bool = false;
		Connection con = null;
		String sql = "INSERT INTO `posting` VALUES(?,?,?,?,?,?,?)";
		Timestamp time = new Timestamp(posting.getTime().getTime());
		Object params[] = {posting.getPid(),posting.getTitle(),posting.getContent(),time,posting.getStatus(),posting.getTid(),posting.getUid()};
		try {
			con = JdbcUtil.getConnection();
			int num = qr.update(con, sql, params);
			if(num>0) {
				bool = true;
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
		return bool;
	}

	/**
	 * 后台审核并修改帖子状态，1为审核通过，2为不通过
	 * @param status
	 * @return
	 */
	@Override
	public boolean updatePostingStatus(int status,int pid) {
		boolean bool = false;
		Connection con = null;
		String sql = "UPDATE `posting` SET `status`=? WHERE pid=?";		
		try {
			con = JdbcUtil.getConnection();
			int num = qr.update(con, sql, status,pid);
			if(num>0) {
				bool = true;
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
		return bool;
	}

	/**
	 * 显示待审核的帖子
	 * @return
	 */
	@Override
	public List<Posting> getPostingToCheck() {
		List<Posting> postingList = null;
		Connection con = null;
		String sql = "SELECT * FROM `posting` WHERE `status` = 0";
		try {
			con = JdbcUtil.getConnection();
			postingList = qr.query(con, sql, new BeanListHandler<Posting>(Posting.class));
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
		return postingList;
	}

	/**
	 * 后台删除帖子
	 * @param status
	 * @return
	 */
	@Override
	public boolean deletePosting(int pid) {
		boolean bool = false;
		Connection con = null;
		String sql = "DELETE FROM `posting` WHERE pid = ?";		
		try {
			con = JdbcUtil.getConnection();
			int num = qr.update(con, sql, pid);
			if(num>0) {
				bool = true;
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
		return bool;
	}

	/**
	 * 根据用户id查询帖子
	 * @param tid
	 * @return
	 */
	@Override
	public List<Posting> getPostingByUid(int uid) {
		List<Posting> postingList = null;
		Connection con = null;
		String sql = "SELECT * FROM `posting` WHERE `uid` = ?";
		try {
			con = JdbcUtil.getConnection();
			postingList = qr.query(con, sql, new BeanListHandler<Posting>(Posting.class),uid);
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
		return postingList;
	}

}

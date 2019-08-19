package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Comment;
import cn.hm.dao.CommentDao;
import cn.hm.util.JdbcUtil;

public class CommentDaoImpl implements CommentDao {
	QueryRunner qr = new QueryRunner();

	/**
	 * 添加评论
	 * @param pid
	 * @return
	 */
	@Override
	public boolean addComment(Comment comment) {
		boolean bool = false;
		Connection con = null;
		String sql = "INSERT INTO `COMMENT` VALUES(?,?,?,?,?)";
		Timestamp time = new Timestamp(comment.getTime().getTime());
		Object params[] = {comment.getCid(),time,comment.getComment(),comment.getPid(),comment.getUid()};
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
	 * 通过帖子pid显示评论
	 * @param pid
	 * @return
	 */
	@Override
	public List<Comment> selectCommentByPid(int pid) {
		List<Comment> commentList = null;
		Connection con = null;
		try {
			con = JdbcUtil.getConnection();
			String sql = "SELECT * FROM `COMMENT` WHERE pid = ? ";
			commentList = qr.query(con, sql, new BeanListHandler<Comment>(Comment.class),pid);		
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
		return commentList;
	}

}

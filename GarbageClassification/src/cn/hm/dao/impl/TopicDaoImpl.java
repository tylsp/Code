package cn.hm.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hm.bean.Topic;
import cn.hm.dao.TopicDao;
import cn.hm.util.JdbcUtil;

public class TopicDaoImpl implements TopicDao {

	QueryRunner qr = new QueryRunner();
	/**
	 * 获得所有帖子主题类别
	 */
	@Override
	public List<Topic> getAllTopic() {
		List<Topic> topiclist = null;
		Connection con = null;
		String sql = "SELECT * FROM `topic`";
		try {
			con = JdbcUtil.getConnection();
			topiclist = qr.query(con, sql, new BeanListHandler<Topic>(Topic.class));
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
		return topiclist;
	}

}

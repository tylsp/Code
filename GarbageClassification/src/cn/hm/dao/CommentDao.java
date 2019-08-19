package cn.hm.dao;

import java.util.List;

import cn.hm.bean.Comment;

public interface CommentDao {
	/**
	 * 添加评论
	 * @param pid
	 * @return
	 */
	boolean addComment(Comment comment);
	/**
	 * 通过帖子pid显示评论
	 * @param pid
	 * @return
	 */
	List<Comment> selectCommentByPid(int pid);
}

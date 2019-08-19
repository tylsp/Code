package cn.hm.service;

import java.util.List;

import cn.hm.bean.Comment;

public interface CommentService {
	/**
	 * 添加评论
	 * @param pid
	 * @return
	 */
	String addComment(Comment comment);
	/**
	 * 通过帖子pid显示评论
	 * @param pid
	 * @return
	 */
	List<Comment> selectCommentByPid(int pid);
}

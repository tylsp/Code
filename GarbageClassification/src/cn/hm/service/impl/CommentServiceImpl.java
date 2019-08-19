package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Comment;
import cn.hm.dao.CommentDao;
import cn.hm.dao.impl.CommentDaoImpl;
import cn.hm.service.CommentService;

public class CommentServiceImpl implements CommentService {

	private CommentDao dao = new CommentDaoImpl(); 
	/**
	 * 添加评论
	 * @param pid
	 * @return
	 */
	@Override
	public String addComment(Comment comment) {
		String message = null;
		if(dao.addComment(comment)) {
			message = "评论成功";
		}else {
			message = "评论失败";
		}
		return message;
	}

	/**
	 * 通过帖子pid显示评论
	 * @param pid
	 * @return
	 */
	@Override
	public List<Comment> selectCommentByPid(int pid) {
		// TODO Auto-generated method stub
		return dao.selectCommentByPid(pid);
	}

}

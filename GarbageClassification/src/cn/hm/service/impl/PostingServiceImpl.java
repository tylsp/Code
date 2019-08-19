package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Posting;
import cn.hm.dao.PostingDao;
import cn.hm.dao.impl.PostingDaoImpl;
import cn.hm.service.PostingService;

public class PostingServiceImpl implements PostingService {

	PostingDao dao = new PostingDaoImpl();
	/**
	 * 查询所有的帖子
	 * @return
	 */
	@Override
	public List<Posting> getAllPosting() {
		// TODO Auto-generated method stub
		return dao.getAllPosting();
	}

	/**
	 * 根据帖子主题id查询帖子
	 * @param tid
	 * @return
	 */
	@Override
	public List<Posting> getPostingByTid(int tid) {
		// TODO Auto-generated method stub
		return dao.getPostingByTid(tid);
	}

	/**
	 * 插入帖子到数据库
	 * @param posting
	 * @return
	 */
	@Override
	public void insertPosting(Posting posting) {
		dao.insertPosting(posting);
	}

	/**
	 * 后台审核并修改帖子状态，1为审核通过，2为不通过
	 * @param status
	 * @return
	 */
	@Override
	public void updatePostingStatus(int status, int pid) {
		// TODO Auto-generated method stub
		dao.updatePostingStatus(status, pid);
	}

	/**
	 * 显示待审核的帖子
	 * @return
	 */
	@Override
	public List<Posting> getPostingToCheck() {
		// TODO Auto-generated method stub
		return dao.getPostingToCheck();
	}

	/**
	 * 后台删除帖子
	 * @param status
	 * @return
	 */
	@Override
	public void deletePosting(int pid) {
		dao.deletePosting(pid);
	}
	
	/**
	 * 根据用户id查询帖子
	 * @param tid
	 * @return
	 */
	@Override
	public List<Posting> getPostingByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.getPostingByUid(uid);
	}

}

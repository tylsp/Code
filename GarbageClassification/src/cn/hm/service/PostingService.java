package cn.hm.service;

import java.util.List;

import cn.hm.bean.Posting;

public interface PostingService {

	/**
	 * 查询所有的帖子
	 * @return
	 */
	List<Posting> getAllPosting();
	/**
	 * 根据帖子主题id查询帖子
	 * @param tid
	 * @return
	 */
	List<Posting> getPostingByTid(int tid);
	
	/**
	 * 插入帖子到数据库
	 * @param posting
	 * @return
	 */
	void insertPosting(Posting posting) ;
	

	/**
	 * 后台审核并修改帖子状态，1为审核通过，2为不通过
	 * @param status
	 * @return
	 */
	void updatePostingStatus(int status,int pid);
	
	/**
	 * 显示待审核的帖子
	 * @return
	 */
	List<Posting> getPostingToCheck();
	
	/**
	 * 后台删除帖子
	 * @param status
	 * @return
	 */
	void deletePosting(int pid);
	
	/**
	 * 根据用户id查询帖子
	 * @param tid
	 * @return
	 */
	List<Posting> getPostingByUid(int uid);
}

package cn.hm.service;

import java.util.List;

import cn.hm.bean.Topic;

public interface TopicService {
	/**
	 * 获得所有帖子主题类别
	 * @return
	 */
	List<Topic> getAllTopic();
	
}

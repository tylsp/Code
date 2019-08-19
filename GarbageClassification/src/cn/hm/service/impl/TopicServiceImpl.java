package cn.hm.service.impl;

import java.util.List;
import cn.hm.bean.Topic;
import cn.hm.dao.TopicDao;
import cn.hm.dao.impl.TopicDaoImpl;
import cn.hm.service.TopicService;

public class TopicServiceImpl implements TopicService {
	TopicDao dao = new TopicDaoImpl();

	/**
	 * 获得所有帖子主题类别
	 * @return
	 */
	@Override
	public List<Topic> getAllTopic() {		
		return dao.getAllTopic();
	}

}

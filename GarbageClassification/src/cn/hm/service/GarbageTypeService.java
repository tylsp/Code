package cn.hm.service;

import java.util.List;

import cn.hm.bean.GarbageType;

public interface GarbageTypeService {
	/**
	 * 选择所有分类
	 * @return
	 */
	List<GarbageType> getAllType();
	/**
	 * 根据tid得到垃圾类别
	 * @return
	 */
	GarbageType getTypeByTid(int tid);
}

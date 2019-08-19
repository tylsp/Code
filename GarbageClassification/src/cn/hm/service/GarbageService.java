package cn.hm.service;

import java.util.List;

import cn.hm.bean.Garbage;

public interface GarbageService {
	/**
	 * 按垃圾名称模糊查询
	 * @param name
	 * @return
	 */
	List<Garbage> selectByName(String name);
	/**
	 * 按垃圾类别显示垃圾
	 * @param type
	 * @return
	 */
	List<Garbage> selctByType(int tid);
}

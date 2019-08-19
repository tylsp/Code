package cn.hm.dao;

import java.util.List;

import cn.hm.bean.Areas;

public interface AreasDao {
	List<Areas> selectArea(String cityid);
}

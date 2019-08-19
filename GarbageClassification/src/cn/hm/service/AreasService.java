package cn.hm.service;

import java.util.List;

import cn.hm.bean.Areas;

public interface AreasService {
	List<Areas> selectArea(String cityid);
}

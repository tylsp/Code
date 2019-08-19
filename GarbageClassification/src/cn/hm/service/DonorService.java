package cn.hm.service;

import java.util.List;

import cn.hm.bean.Donor;

public interface DonorService {
	//捐款
	String addDonor(Donor donor);
	//查询所有的捐款单
	List<Donor> getAllDonor();
	//按用户查询捐款单
	List<Donor> getDonorByUid(int uid);
}

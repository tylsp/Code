package cn.hm.dao;

import java.util.List;

import cn.hm.bean.Donor;

public interface DonorDao {
	//捐款
	boolean addDonor(Donor donor);
	//查询所有的捐款单
	List<Donor> getAllDonor();
	//按用户查询捐款单
	List<Donor> getDonorByUid(int uid);
}

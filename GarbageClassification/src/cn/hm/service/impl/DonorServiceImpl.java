package cn.hm.service.impl;

import java.util.List;

import cn.hm.bean.Donor;
import cn.hm.dao.DonorDao;
import cn.hm.dao.impl.DonorDaoImpl;
import cn.hm.service.DonorService;

public class DonorServiceImpl implements DonorService {

	private DonorDao dao = new DonorDaoImpl();
	@Override
	public String addDonor(Donor donor) {
		// TODO Auto-generated method stub
		String message = null;
		if(dao.addDonor(donor)) {
			message="捐款成功！";
		}else {
			message="捐款失败";
		}
		return message;
	}

	@Override
	public List<Donor> getAllDonor() {
		// TODO Auto-generated method stub
		return dao.getAllDonor();
	}

	@Override
	public List<Donor> getDonorByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.getDonorByUid(uid);
	}

}

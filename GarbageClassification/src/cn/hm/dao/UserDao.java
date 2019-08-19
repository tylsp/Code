package cn.hm.dao;

import cn.hm.bean.User;

public interface UserDao {
    User getUserByUsername(String username);
	
	User getUserByPhone(String phone);
	
	boolean updatePassword(int uid,String password);
		
	User getUserByUid(int uid);
	
	int addUser(User user);
	

}

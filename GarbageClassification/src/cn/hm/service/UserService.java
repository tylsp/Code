/**
 * 
 */
package cn.hm.service;

import cn.hm.bean.User;

public interface UserService {
	String getUserByUsername(String username);
	
	String getUserByPhone(String phone);
	
	
	String loginByUsername(User user);
	
	String loginByPhone(String phone,int scode,int code);
	
	
	String updatePassword(int uid,String oldPassword,String newPassword);
	
    //用于账号密码登陆后Session里存放User
    User getUserByName(String username);
    
  //用于手机登陆后Session里存放User
    User getUserByTel(String phone);

	boolean addUser(User user);
	
	User getUserByUid(int uid);
    
}

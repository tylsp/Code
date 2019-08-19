package cn.hm.service.impl;

import cn.hm.bean.User;
import cn.hm.dao.UserDao;
import cn.hm.dao.impl.UserDaoImpl;
import cn.hm.service.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao dao = new UserDaoImpl();
//
	@Override
	public String loginByUsername(User user) {
		String msg = null;
		User guser = dao.getUserByUsername(user.getUsername());
		// 验证账号是否存在
		if (guser == null) {
			msg = "账号不存在";
			return msg;
		}
		// 验证密码是否正确
		if (!guser.getPassword().equals(user.getPassword())){
			msg = "密码错误";
		}
		return msg;
	}

	@Override
	public String loginByPhone(String phone, int scode, int code) {
		String msg = null;
		// 1.判断phone账号是否存在
		User guser = dao.getUserByPhone(phone);
		// 验证账号是否存在
		if (guser == null) {
			return "手机号未注册！";
		}
		// 验证验证码是否正确
		if (scode != code) {
			msg = "验证码错误！";
		}
		return msg;
	}

	/*
	 * 判断用户名是否被注册过
	 */
	@Override
	public String getUserByUsername(String username) {
		String message="";
		User user=null;
		user=dao.getUserByUsername(username);
		if(user!=null) {
			message="用户名已存在，请重新输入用户名";
		}
		return message;
	}
	/*
	 * 判断手机号是否被注册过
	 */
	@Override
	public String getUserByPhone(String phone) {
		String message="";
		User register=null;
		register=dao.getUserByPhone(phone);
		if(register!=null) {
			message="手机号已经注册";
		}
		return message;
	}
	/*
	 * 判断用户数据是否插入成功
	 */
	@Override
	public boolean addUser(User user) {
		int num=dao.addUser(user);
		if(num>0) {
			//插入用户数据成功
			System.out.println("数据插入成功");
			return true;
		}
		System.out.println("数据插入失败");
		return false;
	}
	
	@Override
	public User getUserByName(String username) {
		// TODO Auto-generated method stub
		return dao.getUserByUsername(username);
	}

	@Override
	public User getUserByTel(String phone) {
		// TODO Auto-generated method stub
		return dao.getUserByPhone(phone);
	}


	@Override
	public String updatePassword(int uid, String oldPassword, String newPassword) {
		String msg = null;
		boolean bool = dao.updatePassword(uid,oldPassword);
		// 验证账号是否存在
		if (!bool) {
			msg = "原密码错误！";
			return msg;
		}
		dao.updatePassword(uid, newPassword);
		return "OK!";
	}

	@Override
	public User getUserByUid(int uid) {
		// TODO Auto-generated method stub
		return dao.getUserByUid(uid);
	}


}

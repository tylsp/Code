package cn.hm.dao.impl;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.sun.org.apache.xpath.internal.operations.And;

import cn.hm.bean.User;
import cn.hm.dao.UserDao;
import cn.hm.util.JdbcUtil;

public class UserDaoImpl implements UserDao {	
		QueryRunner qr = new QueryRunner();
		/*
		 * 通过用户名查询用户
		 */
		@Override
		public User getUserByUsername(String username) {
			Connection con = null;
			User user = null;
			try {
				//1.获取connection
				con = JdbcUtil.getConnection();
				String sql = "select * from user where username = ?";
				//2.执行查询操作
				System.out.println(username+"------------------");
				user = qr.query(con, sql, new BeanHandler<User>(User.class), username);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//3.将Connection归还给连接池
				if(con!=null) {
					try {
						JdbcUtil.realase(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//4.返回结果
			return user;
		}
		/*
		 * 根据手机号查询用户
		 */
		@Override
		public User getUserByPhone(String phone) {
			Connection con = null;
			User user = null;
			try {
				//1.获取connection
				con = JdbcUtil.getConnection();
				String sql = "select * from user  where phone = ?";
				//2.执行查询操作
				user = qr.query(con, sql, new BeanHandler<User>(User.class), phone);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//3.将Connection归还给连接池
				
				if(con!=null) {
					try {
						JdbcUtil.realase(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//4.返回结果
			return user;
		}
		/*
		 * 添加用户信息
		 */
		@Override
		public int addUser(User user) {
			Connection con = null;
			int num=0;
			try {
				//1.获取connection
				con = JdbcUtil.getConnection();
				String sql = "insert into user(uid,username,password,phone) value (?,?,?,?)";
				//2.执行修改操作
				num = qr.update(con, sql, user.getUid(),user.getUsername(),user.getPassword(),user.getPhone());
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//3.将Connection归还给连接池
				if(con!=null) {
					try {
						JdbcUtil.realase(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//4.返回结果
			return num;
		}
		/*
		 * 根据uid修改密码
		 * 
		 */
		@Override
		public boolean updatePassword(int uid, String password) {
			Connection con = null;
			boolean result = false;
			try {
				//1.获取connection
				con = JdbcUtil.getConnection();
				String sql = "update user set `password` = ? where uid = ?";
				//2.执行修改操作
				int num  = qr.update(con, sql, password,uid);
				if(num>0) {
					result = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//3.将Connection归还给连接池
				if(con!=null) {
					try {
						JdbcUtil.realase(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//4.返回结果
			return result;
		}
		/*
		 * 根据uid查询用户		
		 */
		@Override
		public User getUserByUid(int uid) {
			Connection con = null;
			User user = null;
			try {
				//1.获取connection
				con = JdbcUtil.getConnection();
				String sql = "select * from user  where uid = ?";
				//2.执行查询操作
				user = qr.query(con, sql, new BeanHandler<User>(User.class), uid);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				//3.将Connection归还给连接池
				if(con!=null) {
					try {
						JdbcUtil.realase(con);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			//4.返回结果
			return user;
		}
	
		
}
		





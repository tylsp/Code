package cn.hm.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

//1.dao层的connection 与 con 同一个。
public class JdbcUtil {
	//c3p0-config.xml加载对应参数，形成连接池
    private static ComboPooledDataSource datasource = new ComboPooledDataSource("mysql");
    
    //处理多线程并发，为每一个线程添加connection
    private static ThreadLocal<Connection> local = new ThreadLocal<Connection>();
    
    public static Connection getConnection() throws SQLException {  	
    	//获得connection 对象
    	Connection con=local.get();
    	if(con!=null) 
    		return con;
    	return datasource.getConnection();
    }
    
    public static DataSource getDataSource() {
    	return datasource;
    }
    
    //事务启动
    public static void beginTransaction() throws SQLException {
    	Connection con=local.get();
    	if(con!=null) {
    		throw new SQLException("事务已经开启，不能重复启动！");
    	}
    	con=getConnection();
    	//开始事务
    	con.setAutoCommit(false);
    	local.set(con);
    }
    
    public static void commitTransaction() throws SQLException {
    	Connection con=local.get();
    	if(con==null) {
    		throw new SQLException("事务未启动，不能提交！");
    	}
    	con.commit();
    	con.close();
    	local.remove();
    }
    
    public static void rollbackTranaction() throws SQLException {
    	Connection con=local.get();
    	if(con==null) {
    		throw new SQLException("事务未启动，不能提交！");
    	}
    	con.rollback();
    	con.close();
    	local.remove();
    }
    
    public static void realase(Connection connection) throws SQLException { 	
    	Connection con=local.get();
    	//如果con为空，说明当前无事务，可以关闭。
    	if(con == null) 
    	    connection.close();
    	//connection 与 con不相等  说明connection非事务conection对象
    	if(con != connection) 
			connection.close();
		
    		
    }
    
}

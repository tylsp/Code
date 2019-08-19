package cn.hm.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hm.bean.CallItem;
import cn.hm.bean.CallOrder;
import cn.hm.bean.CallOrderItem;
import cn.hm.bean.PageBean;
import cn.hm.dao.CallOrderDao;
import cn.hm.util.JdbcUtil;

public class CallOrderDaoImpl implements CallOrderDao {

	QueryRunner qr = new QueryRunner();

	@Override
	public void createOrder(CallOrder co) throws SQLException {
		// TODO Auto-generated method stub
		List<CallItem> list = null;
		Connection conn = null;
		conn = JdbcUtil.getConnection();
		String sql = "INSERT INTO `callorder` VALUES(?,?,?,?,?,?,?,SYSDATE(),?,?)";
		Timestamp time = new Timestamp(co.getOrdertime().getTime());
		qr.update(conn, sql, co.getOid(), time, co.getUid(), co.getCtotal(), co.getState(), "未填写", "未填写", "未填写", "未填写");
	}

	@Override
	public void addCallOrderItems(List<CallOrderItem> orderlist) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = null;
		con = JdbcUtil.getConnection();
		String sql = "insert into callorderitem values(?,?,?,?,?)";
		Object params[][] = new Object[orderlist.size()][];
		for (int i = 0; i < orderlist.size(); i++) {
			CallOrderItem item = orderlist.get(i);
			params[i] = new Object[] { item.getIid(), item.getOid(), item.getCallItem().getCid(), item.getIweight(),
					item.getSubtotal()};
		}
		// 批量执行 参数是二维数组
		qr.batch(con,sql, params);
	}
	
	@Override
	public List<CallOrder> getCallOrder(int uid, int state) {
		// TODO Auto-generated method stub
		List<CallOrder> orderlist = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = null;
			if(state == -1){
				sql = "SELECT * FROM `callorder` WHERE `uid` = ?";
				orderlist = qr.query(conn, sql, new BeanListHandler<CallOrder>(CallOrder.class),uid);
			}else{
				sql = "SELECT * FROM `callorder` WHERE `uid` = ? AND state = ?";
				orderlist = qr.query(conn, sql, new BeanListHandler<CallOrder>(CallOrder.class),uid,state);
			}
			for (CallOrder callOrder : orderlist) {
				List<CallOrderItem> corderitemlist = new ArrayList<CallOrderItem>();
				sql = "SELECT * FROM `callorderitem`,`callitem` WHERE callorderitem.cid = callitem.cid AND `oid` = ?";
				List<Map<String,Object>> maplist = qr.query(conn, sql, new MapListHandler(), callOrder.getOid());
				for (Map<String,Object> item : maplist) {
					CallOrderItem coi = new CallOrderItem();
					CallItem ci = new CallItem();
					BeanUtils.populate(coi, item);
					BeanUtils.populate(ci, item);
					coi.setCallItem(ci);
					corderitemlist.add(coi);
				}
				callOrder.setCorderitemlist(corderitemlist);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return orderlist;
	}

	@Override
	public boolean updateStatus(String oid, int status) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "UPDATE `callorder` SET `state` = ? WHERE oid = ?";
			int len = qr.update(conn, sql, status, oid);
			if(len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean cancelOrder(String oid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "DELETE FROM `callorder` WHERE oid = ?";
			int len = qr.update(conn, sql, oid);
			if(len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean setProtities(String iid, double iweight, double subtotal) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "UPDATE `callorderitem` SET `iweight` = ?,`subtotal` = ? WHERE `iid` = ?";
			int len = qr.update(conn, sql, iweight, subtotal, iid);
			if(len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean setCTotal(String oid, double total) {
		// TODO Auto-generated method stub
		//UPDATE `callorder` SET `ctotal` = 10 WHERE oid = 'C4AE88CE37D04D4DA48D57926FFB6500'
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "UPDATE `callorder` SET `ctotal` = ? WHERE oid = ?";
			int len = qr.update(conn, sql, total, oid);
			if(len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean setFormProtities(String oid, String oname, String otel, Date gotime, String address,
			String remark) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "UPDATE `callorder` SET `state` = 2,`oname` = ?,`otel` = ?,`gotime` = ? ,`address` = ?,`remark` = ? WHERE `oid` = ?";
			int len = qr.update(conn, sql, oname, otel, gotime, address, remark, oid);
			if(len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public PageBean<CallOrder> getOrderControl(int... value) {
		// TODO Auto-generated method stub
		PageBean<CallOrder> page = new PageBean<CallOrder>();
		Connection conn = null;
		int currentPage = value[0];
		int pageSize = value[1];
		page.setPageSize(pageSize);
		try {
			conn = JdbcUtil.getConnection();
			if(value.length == 2){
				String sql = "SELECT COUNT(*) FROM `callorder`";
				Number count = qr.query(conn, sql, new ScalarHandler<Number>());
				page.setCount(count.intValue());
				page.setCurrentPage(currentPage);
				sql = "SELECT * FROM `callorder` LIMIT ?,?";
				//System.out.println("进入dao---2参方法");
				page.setList(qr.query(conn, sql, new BeanListHandler<CallOrder>(CallOrder.class),(page.getCurrentPage() - 1)*page.getPageSize(),page.getPageSize()));
			}else{
				String sql = "SELECT COUNT(*) FROM `callorder` WHERE state = ?";
				Number count = qr.query(conn, sql, new ScalarHandler<Number>(),value[2]);
				page.setCount(count.intValue());
				sql = "SELECT * FROM `callorder` WHERE `state` = ? LIMIT ?,?";
				//System.out.println("进入dao---3参方法");
				//System.out.println("总页数----"+page.getCountPage());
				page.setCurrentPage(currentPage);
				page.setList(qr.query(conn, sql, new BeanListHandler<CallOrder>(CallOrder.class),value[2],(page.getCurrentPage() - 1)*page.getPageSize(),page.getPageSize()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					JdbcUtil.realase(conn);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return page;
	}

	@Override
	public boolean checkState(String oid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "SELECT `state` FROM `callorder` WHERE `oid` = ?";
			Number state = qr.query(conn, sql, new ScalarHandler<Number>(), oid);
			int waitState = state.intValue();
			if(waitState == 3){
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public int getCidByiid(String iid) {
		// TODO Auto-generated method stub
		Connection conn = null;
		int cid = 0;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "SELECT cid FROM callorderitem WHERE iid = ?";
			Number id = qr.query(conn, sql, new ScalarHandler<Number>(), iid);
			cid = id.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cid;
	}

}

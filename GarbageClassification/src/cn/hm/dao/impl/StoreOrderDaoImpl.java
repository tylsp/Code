package cn.hm.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hm.bean.Goods;
import cn.hm.bean.Page;
import cn.hm.bean.StoreOrder;
import cn.hm.bean.StoreOrderItem;
import cn.hm.dao.StoreOrderDao;
import cn.hm.util.JdbcUtil;

public class StoreOrderDaoImpl implements StoreOrderDao {
	private QueryRunner qr = new QueryRunner();

	@Override
	public boolean updateState(String oid, int ostate) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "update `orders` set `ostate` = ? where `oid` = ?";
		try {
			con = JdbcUtil.getConnection();
			int line = qr.update(con, sql, ostate, oid);
			if (line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public boolean createStoreOrder(StoreOrder order) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "insert into `orders` values (?,?,?,?,?,?,?,?)";
		try {
			con = JdbcUtil.getConnection();
			Timestamp time = new Timestamp(order.getOrdertime().getTime());
			Object[] params = { order.getOid(), time, order.getOtotal(), order.getOstate(), order.getUid(),
					order.getAddress(), order.getDeaddress(),order.getPhone() };
			int line = qr.update(con, sql, params);
			if (line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public List<StoreOrder> getStoreOrder(int uid, int ostate) {
		// TODO Auto-generated method stub
		List<StoreOrder> list = null;
		Connection con = null;
		String sql = null;
		try {
			con = JdbcUtil.getConnection();
			if (ostate == -1) {
				sql = "select * from `orders` where `uid` = ?";
				list = qr.query(con, sql, new BeanListHandler<StoreOrder>(StoreOrder.class), uid);
			} else {
				sql = "select * from `orders` where `uid` = ? and `ostate` = ?";
				list = qr.query(con, sql, new BeanListHandler<StoreOrder>(StoreOrder.class), uid, ostate);
			}
			for (StoreOrder order : list) {
				order.setList(getStoreOrderItem(order.getOid()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public boolean createStoreOrderItem(List<StoreOrderItem> list) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "insert into `orderitem` values (null,?,?,?,?)";
		try {
			con = JdbcUtil.getConnection();
			Object[][] params = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				StoreOrderItem item = list.get(i);
				params[i] = new Object[] { item.getCount(), item.getOsubtotal(), item.getOid(),
						item.getGoods().getGid() };
			}
			int[] line = qr.batch(con, sql, params);
			if (line != null) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public List<StoreOrderItem> getStoreOrderItem(String oid) {
		// TODO Auto-generated method stub
		List<StoreOrderItem> list = new ArrayList<StoreOrderItem>();
		List<Map<String, Object>> maps = null;
		Connection con = null;
		String sql = "select * from `goods` a,`orderitem` b where a.gid = b.gid and b.oid = ?";
		try {
			con = JdbcUtil.getConnection();
			maps = qr.query(con, sql, new MapListHandler(), oid);
			for (Map<String, Object> map : maps) {
				StoreOrderItem item = new StoreOrderItem();
				Goods goods = new Goods();
				try {
					BeanUtils.populate(item, map);
					BeanUtils.populate(goods, map);
					item.setGoods(goods);
					list.add(item);
				} catch (IllegalAccessException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public StoreOrder getOrder(String oid) {
		// TODO Auto-generated method stub
		StoreOrder order = null;
		Connection con = null;
		String sql = "select * from `orders` where `oid` = ?";
		try {
			con = JdbcUtil.getConnection();
			order = qr.query(con, sql, new BeanHandler<StoreOrder>(StoreOrder.class), oid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return order;
	}

	@Override
	public boolean deleteOrderItem(String oid) throws SQLException {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "delete from `orderitem` where `oid` = ?";

		con = JdbcUtil.getConnection();
		int line = qr.update(con, sql, oid);
		if (line > 0) {
			bool = true;
		}

		return bool;
	}

	@Override
	public boolean deleteOrder(String oid) throws SQLException {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "delete from `orders` where `oid` = ?";

		con = JdbcUtil.getConnection();
		int line = qr.update(con, sql, oid);
		if (line > 0) {
			bool = true;
		}

		return bool;
	}

	@Override
	public boolean updateDate(Date date, String oid) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection con = null;
		String sql = "update `orders` set `ordertime` = ? where `oid` = ?";
		Timestamp time = new Timestamp(date.getTime());
		Object[] params = { time, oid };
		try {
			con = JdbcUtil.getConnection();
			int line = qr.execute(con, sql, params);
			if (line > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (con != null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return bool;
	}

	@Override
	public Page<StoreOrder> findAllOrder(int currentPageNo, int pageSize,int ostate) {
		Page<StoreOrder> page = new Page<StoreOrder>();
		List<StoreOrder> list  =null;
		page.setPageSize(pageSize);
		Connection con = null;
		String sql = null;
		Number num = null;
		try {
			con = JdbcUtil.getConnection();
			// 查询数据总量
			// 查询当前页数据
			if(ostate == -1) {	
				sql = "select count(*) from `orders`";
				num = qr.query(con, sql, new ScalarHandler<Number>());
				page.setCountRecord(num.intValue());
				page.setCurrentPageNo(currentPageNo);
				sql = "select * from `orders` limit ?,?";
				list = qr.query(con, sql, new BeanListHandler<StoreOrder>(StoreOrder.class),
						(page.getCurrentPageNo() - 1) * page.getPageSize(), page.getPageSize());
			}else {
				sql = "select count(*) from `orders` where `ostate` = ?";
				num = qr.query(con, sql, new ScalarHandler<Number>(),ostate);
				page.setCountRecord(num.intValue());
				page.setCurrentPageNo(currentPageNo);
				sql = "select * from `orders` where `ostate` = ? limit ?,? ";
				list = qr.query(con, sql, new BeanListHandler<StoreOrder>(StoreOrder.class),ostate,
						(page.getCurrentPageNo() - 1) * page.getPageSize(), page.getPageSize());
			}
			page.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(con!=null) {
				try {
					JdbcUtil.realase(con);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return page;

	}

	@Override
	public Page<StoreOrder> findAllOrderUser(int currentPageNo, int pageSize, int ostate, int uid) {
		Page<StoreOrder> page = new Page<StoreOrder>();
		List<StoreOrder> list  =null;
		page.setPageSize(pageSize);
		Connection con = null;
		String sql = null;
		Number num = null;
			try {
				con = JdbcUtil.getConnection();
				if(ostate == -1) {
				sql = "select count(*) from `orders` where `uid` = ?";
				num = qr.query(con, sql, new ScalarHandler<Number>(),uid);

				page.setCountRecord(num.intValue());
				page.setCurrentPageNo(currentPageNo);
				sql = "select * from `orders` where `uid` = ? limit ?,?";
				list = qr.query(con, sql, new BeanListHandler<StoreOrder>(StoreOrder.class),uid,
						(page.getCurrentPageNo() - 1) * page.getPageSize(), page.getPageSize());
				}else {
					sql = "select count(*) from `orders` where `uid` = ? and `ostate` = ?";
					num = qr.query(con, sql, new ScalarHandler<Number>(),uid,ostate);
					page.setCountRecord(num.intValue());
					page.setCurrentPageNo(currentPageNo);
					sql = "select * from `orders` where `uid` = ? and `ostate` = ? limit ?,? ";
					list = qr.query(con, sql, new BeanListHandler<StoreOrder>(StoreOrder.class),uid,ostate,
							(page.getCurrentPageNo() - 1) * page.getPageSize(), page.getPageSize());
				}
				for (StoreOrder order : list) {
					order.setList(getStoreOrderItem(order.getOid()));
				}
				
				page.setList(list);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return page;
	}

}

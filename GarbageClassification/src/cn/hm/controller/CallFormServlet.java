package cn.hm.controller;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.CallOrder;
import cn.hm.bean.CallOrderItem;
import cn.hm.service.CallItemService;
import cn.hm.service.CallOrderService;
import cn.hm.service.impl.CallItemServiceImpl;
import cn.hm.service.impl.CallOrderServiceImpl;
import cn.hm.util.BaseServlet;

@WebServlet("/CallFormServlet")
public class CallFormServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private CallOrderService cos = new CallOrderServiceImpl();
	private CallItemService cs = new CallItemServiceImpl();
	
	double mysubtotal = 0;
	Map<String,Double> map = new HashMap<String,Double>();
	Map<String,Double> pricemap = new HashMap<String,Double>();
	
	private String getCode() {
		String code = UUID.randomUUID().toString();
		code = code.replace("-", "").toUpperCase();
		return code;
	}

	// 创建提交的订单
	protected String CreateOrder(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//User user = (User) req.getSession().getAttribute("user");
		CallOrder co = new CallOrder();
		String oid = getCode();
		co.setOid(oid);
		co.setOrdertime(new Date());
		int uid = 1;//更换
		co.setUid(uid);
		co.setState(1);
		
		List<CallOrderItem> itemList = new ArrayList<CallOrderItem>();
		List<Integer> cidList = cs.getAllCid();
		for (Integer cid : cidList) {
			String has = req.getParameter(cid + "");
			if(has != null && !"".equals(has)) {
				CallOrderItem coi = new CallOrderItem();
				coi.setCallItem(cs.getCallItemByCid(cid));
				coi.setIid(getCode());
				coi.setOid(oid);
				itemList.add(coi);
			}
		}
		//double total = 0;
		co.setCorderitemlist(itemList);
		cos.createOrder(co);
		//System.out.println(co);
		req.setAttribute("CallOrder", co);
		return "forward:/service_on_call/order.jsp";
	}
	
	// 计算小计(修改weight和subtotal)
	protected String getSubtotal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double weight = Double.parseDouble(req.getParameter("weight"));
		double price = Double.parseDouble(req.getParameter("price"));
		String iid = req.getParameter("iid");
		String oid = req.getParameter("oid");
		
		map.put(iid, weight);
		System.out.println(map);
		
		price = cs.searchPriceByiid(cos.getCidByiid(iid));
		pricemap.put(iid, price);
		System.out.println(pricemap);
		
		double subtotal = weight*price;
		cos.setProtities(iid, weight, subtotal);
		
		Collection<Double> coll = map.values();
		Collection<Double> pcoll = pricemap.values();
		double total = 0;
		
		Iterator<Double> it = coll.iterator();
		Iterator<Double> it1 = pcoll.iterator();
		
		while(it.hasNext()){
			total += it.next() * it1.next();
			System.out.println(total); //100 3.14 false
		}
		/*for(int i = 0; i < coll.size(); i++){
			total += coll.iterator().next() * pcoll.iterator().next();
			System.out.println(total);
		}*/
		
		/*for(Double sub : coll){
			total += sub * price;
			System.out.println(total);
		}*/
		cos.setCTotal(oid, total);
		resp.getWriter().write(total+"");
		return null;
	}
	
	//订单取消
	protected String cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String oid = req.getParameter("oid");
		//System.out.println(oid);
		cos.cancelOrder(oid);
		cs.deleteIidByOid(oid);
		return "redirect:/GarbageClassification/service_on_call/cancelOrder.jsp";
	}
	
	//订单提交
	protected String submitOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, ParseException {
		//System.out.println("修改信息------进入--");
		String oid = req.getParameter("oid");
		//System.out.println(oid);
		String oname = req.getParameter("oname");
		String otel = req.getParameter("otel");
		String gotime = req.getParameter("gotime");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date =  sdf.parse(gotime);
		
		String cityPlace = req.getParameter("cityPlace");
		String city = cityPlace.substring(cityPlace.lastIndexOf(":")+1);
		String xiangxi = req.getParameter("xiangxi");
		String address = city + xiangxi;
		
		String remark = req.getParameter("remark");
		boolean bool = cos.setFormProtities(oid, oname, otel, date, address, remark);
		//System.out.println(bool);
		req.setAttribute("oid", oid);
		return "forward:/service_on_call/submitOrder.jsp";
	}

	// 我的订单
	protected String getMyOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.getSession().getAttribute("user");
		String state = req.getParameter("state");
		// System.out.println(state);
		int uid = 1;
		List<CallOrder> list = cos.getCallOrder(uid, Integer.parseInt(state));
		String json = JSONArray.toJSONString(list);
		resp.getWriter().write(json);
		return null;
	}
		
	//检查该订单的数据库状态是否变化
	protected String checkState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// req.getSession().getAttribute("user");
		String oid = req.getParameter("oid");
		boolean bool = cos.checkState(oid);
		resp.getWriter().write(bool+"");
		return null;
	}
	
}

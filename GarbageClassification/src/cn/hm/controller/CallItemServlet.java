package cn.hm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.CallItem;
import cn.hm.service.CallItemService;
import cn.hm.service.impl.CallItemServiceImpl;
import cn.hm.util.BaseServlet;

@WebServlet("/CallItemServlet")
public class CallItemServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	private CallItemService cs = new CallItemServiceImpl();

	// 获取所有上门服务的项目
	protected String getAllCallItems(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<CallItem> list = cs.getAllCallItems();
		// System.out.println("servlet-------" + list);
		String json = JSONArray.toJSONString(list);
		resp.getWriter().write(json);
		return null;
	}

}

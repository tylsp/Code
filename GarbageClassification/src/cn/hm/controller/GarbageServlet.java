package cn.hm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.Garbage;
import cn.hm.bean.GarbageType;
import cn.hm.service.GarbageService;
import cn.hm.service.GarbageTypeService;
import cn.hm.service.impl.GarbageServiceImpl;
import cn.hm.service.impl.GarbageTypeServiceImpl;
import cn.hm.util.BaseServlet;

@WebServlet("/GarbageServlet")
public class GarbageServlet extends BaseServlet {
	private GarbageTypeService tservice = new GarbageTypeServiceImpl();
	private GarbageService gservice = new GarbageServiceImpl();
	private static final long serialVersionUID = 1L;
	
	/**
	 * 显示所有分类
	 */
	protected String getAllType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<GarbageType> typeList = tservice.getAllType();
		String json = JSONArray.toJSONString(typeList);
		response.getWriter().write(json);
		return null;
	}

	/**
	 * 按名称模糊查询
	 */
	protected String selectByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("search");
		List<Garbage> garbageList = gservice.selectByName(name);
		for(int i=0;i<garbageList.size();i++) {
			Garbage garbage = garbageList.get(i);
			GarbageType type = tservice.getTypeByTid(garbage.getTid());
			garbage.setCname(type.getName());
		}
		request.setAttribute("garbageList", garbageList);		
		return "/search/result.jsp";
	}
	
	/**
	 * 按垃圾类别显示垃圾列表
	 */
	protected String selectByType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String tid = request.getParameter("tid");
		int ttid = 0;
		if(tid!=null&&!tid.trim().isEmpty()) {
			ttid = Integer.parseInt(tid);
		}
		List<Garbage> garbagelist = gservice.selctByType(ttid);
		request.setAttribute("garbagelist", garbagelist);
		
		GarbageType type = tservice.getTypeByTid(ttid);
		request.setAttribute("type", type);
		return "/search/garbageDetails.jsp";
	}
}

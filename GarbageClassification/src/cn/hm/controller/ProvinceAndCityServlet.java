package cn.hm.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.Areas;
import cn.hm.bean.Cities;
import cn.hm.bean.Provinces;
import cn.hm.service.AreasService;
import cn.hm.service.CitiesService;
import cn.hm.service.ProvincesService;
import cn.hm.service.impl.AreasServiceImpl;
import cn.hm.service.impl.CitiesServiceImpl;
import cn.hm.service.impl.ProvincesServiceImpl;
import cn.hm.util.BaseServlet;
@WebServlet("/ProvinceAndCityServlet")
public class ProvinceAndCityServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	private ProvincesService pService = new ProvincesServiceImpl();
	private CitiesService cService = new CitiesServiceImpl();
	private AreasService aService = new AreasServiceImpl();
	protected String province(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int count = Integer.valueOf(request.getParameter("count"));
		int gid = Integer.valueOf(request.getParameter("gid"));
		List<Provinces> list = pService.selectProvice();
		request.setAttribute("count", count);
		request.setAttribute("gid", gid);
		request.setAttribute("list", list);
		return "/CartServlet?method=addItem";
	}
	protected String myProvince(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Provinces> list = pService.selectProvice();
		request.setAttribute("list", list);
		return "/CartServlet?method=getMyCart";
	}
	protected String buy(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int count = Integer.valueOf(request.getParameter("count"));
		int gid = Integer.valueOf(request.getParameter("gid"));
		List<Provinces> list = pService.selectProvice();
		request.setAttribute("count", count);
		request.setAttribute("gid", gid);
		request.setAttribute("list", list);
		return "/CartServlet?method=buyNow";
	}

	protected String City(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String provice = request.getParameter("provice");
		Provinces provinces = pService.selectPro(provice);
		List<Cities> list = cService.selectCity(provinces.getProvinceid());
		String str = JSONArray.toJSONString(list);
		response.getWriter().write(str);
		return null;
	}
	
	protected String Area(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String city = request.getParameter("city");
		Cities cities = cService.selectCi(city);
		List<Areas> list = aService.selectArea(cities.getCityid());
		String str = JSONArray.toJSONString(list);
		response.getWriter().write(str);
		return null;
	}
}

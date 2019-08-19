package cn.hm.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.Goods;
import cn.hm.service.GoodsService;
import cn.hm.service.impl.GoodsServiceImpl;
import cn.hm.util.BaseServlet;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/GoodsServlet")
public class GoodsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private GoodsService server = new GoodsServiceImpl();
   
	protected String getAllGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		List<Goods> list = server.getAllGoods();
//		System.out.println(list);
		request.setAttribute("list", list);
		String str = JSONArray.toJSONString(list);
		response.getWriter().write(str);
		return null;
	}

}

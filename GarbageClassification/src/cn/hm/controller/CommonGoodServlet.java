package cn.hm.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.hm.bean.CommonGood;
import cn.hm.service.CommonGoodService;
import cn.hm.service.impl.CommonGoodServiceImpl;
import cn.hm.util.BaseServlet;

@WebServlet("/CommonGoodServlet")
public class CommonGoodServlet extends BaseServlet {

	private CommonGoodService service = new CommonGoodServiceImpl();
	private static final long serialVersionUID = 1L;

	protected String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<CommonGood> commongoods = service.findAll();
		/**
		 *遍历commongoods给每一个CommonGood对象的ctotal设值
		 */
		for(int i=0;i<commongoods.size();i++) {
			CommonGood commongood = commongoods.get(i);
			commongood.setCtotal(commongood.getDonorlist());
		}
		String json = JSONArray.toJSONString(commongoods);
		response.getWriter().write(json);
		return null;
	}
	
	protected String selectByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		int ccid = 0;
		if(cid!=null&!cid.trim().isEmpty()) {
			ccid = Integer.parseInt(cid);
		}
		CommonGood commongood = service.selectByCid(ccid);
		request.setAttribute("commongood", commongood);
		return "/common_good/commonGoodDetails.jsp";
	}
	
}

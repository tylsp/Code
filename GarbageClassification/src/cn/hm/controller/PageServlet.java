package cn.hm.controller;

import cn.hm.bean.Page;
import cn.hm.bean.StoreOrder;
import cn.hm.bean.User;
import cn.hm.service.StoreOrderService;
import cn.hm.service.impl.StoreOrderServiceImpl;
import cn.hm.util.BaseServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private StoreOrderService service = new StoreOrderServiceImpl();
	protected String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("currentPageNo");
		int currentPageNo = 1;
		if(no != null && !no.trim().isEmpty()) {
			currentPageNo = Integer.valueOf(no);
		}
		String size = request.getParameter("pageSize");
		int pageSize = 10;
		if(size != null && !size.trim().isEmpty()) {
			pageSize = Integer.valueOf(size);
		}
		String os = request.getParameter("ostate");
		int ostate = 0;
		if(os != null && !os.trim().isEmpty()) {
			ostate = Integer.valueOf(os);
		}
		Page<StoreOrder> pages = service.findAllOrder(currentPageNo, pageSize,ostate);
		
		request.setAttribute("pages", pages);
		request.setAttribute("ostate", ostate);
		return "/store/background/backgroundOrder.jsp";
	}

	
	protected String findUserOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("currentPageNo");
		int currentPageNo = 1;
		if(no != null && !no.trim().isEmpty()) {
			currentPageNo = Integer.valueOf(no);
		}
		String size = request.getParameter("pageSize");
		int pageSize = 10;
		if(size != null && !size.trim().isEmpty()) {
			pageSize = Integer.valueOf(size);
		}
		String os = request.getParameter("ostate");
		int ostate = 0;
		if(os != null && !os.trim().isEmpty()) {
			ostate = Integer.valueOf(os);
		}
		User user = (User) request.getSession().getAttribute("user");
		int uid = user.getUid();
		Page<StoreOrder> orders = service.findAllOrderUser(currentPageNo, pageSize, ostate, uid);
		request.setAttribute("orders", orders);
		request.setAttribute("ostate", ostate);
		
		return "/store/UserOrders.jsp";
	}

}

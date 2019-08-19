package cn.hm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hm.bean.CallOrder;
import cn.hm.bean.PageBean;
import cn.hm.service.CallOrderService;
import cn.hm.service.impl.CallOrderServiceImpl;
import cn.hm.util.BaseServlet;

@WebServlet("/CallControlServlet")
public class CallControlServlet extends BaseServlet{

	private static final long serialVersionUID = 1L;
	private CallOrderService cos = new CallOrderServiceImpl();
	
	protected String showOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String size = req.getParameter("pageSize");
		String current = req.getParameter("currentPage");
		String btn = req.getParameter("btn");
		int pageSize = 5;
		if(size != null && !size.trim().isEmpty()){
			pageSize = Integer.parseInt(size);
		}
		int currentPage = 1;
		if(current != null && !current.trim().isEmpty()){
			currentPage = Integer.parseInt(current);
		}
		//System.out.println(currentPage);
		PageBean<CallOrder> page = null;
		switch(btn){
			case "1" : page = cos.getOrderControl(currentPage, pageSize);break;
			case "2" : page = cos.getOrderControl(currentPage, pageSize, 1);break;
			case "3" : page = cos.getOrderControl(currentPage, pageSize, 2);break;
			case "4" : page = cos.getOrderControl(currentPage, pageSize, 3);break;
			case "5" : page = cos.getOrderControl(currentPage, pageSize, 4);break;
		}
		
		//System.out.println(page);
		req.setAttribute("page", page);
		req.setAttribute("btnid", btn);
		return "/service_on_call/callControl.jsp";
	}
	
	protected String updateState(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//System.out.println("---进入SERVLET---");
		String oid = req.getParameter("oid");
		//System.out.println("oid----"+oid);
		String update = req.getParameter("update");
		//System.out.println("update----"+update);
		if(update.equals("1")){
			cos.updateStatus(oid, 3);
		}else{
			cos.updateStatus(oid, 4);
		}
		return "/service_on_call/callControl.jsp";
	}

}

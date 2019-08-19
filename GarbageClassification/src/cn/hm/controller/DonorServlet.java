package cn.hm.controller;

import java.io.IOException;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hm.bean.Donor;
import cn.hm.bean.User;
import cn.hm.service.DonorService;
import cn.hm.service.impl.DonorServiceImpl;
import cn.hm.util.BaseServlet;

@WebServlet("/DonorServlet")
public class DonorServlet extends BaseServlet {

	private DonorService service = new DonorServiceImpl();
	private static final long serialVersionUID = 1L;

	/*
	 * 捐款
	 */
	protected String addDonor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建一个捐款单对象
		Donor donor = new Donor();
		//获取输入的钱
		String money = request.getParameter("money");
		double donatemoney=0;
		if(money!=null&&!money.trim().isEmpty()) {
			donatemoney = Double.parseDouble(money);
		}
		donor.setMoney(donatemoney);
		//获取系统时间
		Date date = new Date();
		donor.setDonatetime(date);
		//从session里获取
		User user = (User) request.getSession().getAttribute("user");
		donor.setUser(user);
		donor.setUid(donor.getUser().getUid());
		String cid = request.getParameter("cid");
		int ccid = 0;
		if(cid!=null&&!cid.trim().isEmpty()) {
			ccid = Integer.parseInt(cid);
		}
		donor.setCid(ccid);
		service.addDonor(donor);
		return "/common_good/donateinfo.jsp";
	}
	
	/*
	 * 遍历出所有捐款单
	 */
	protected String getAllDonor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Donor> donorlist = service.getAllDonor();
		request.getSession().setAttribute("donorlist", donorlist);
		return "/common_good/donor.jsp";
	}
	
	/*
	 * 按用户id遍历捐款单
	 */
	protected String getDonorByUid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");		
		List<Donor> donorlist = service.getDonorByUid(user.getUid());
		request.getSession().setAttribute("donorlist", donorlist);
		return "/common_good/user_donor.jsp";
	}
	
	
}

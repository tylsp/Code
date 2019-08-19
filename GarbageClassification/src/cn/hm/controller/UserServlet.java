package cn.hm.controller;

import cn.hm.bean.User;
import cn.hm.service.UserService;
import cn.hm.service.impl.UserServiceImpl;
import cn.hm.util.BaseServlet;
import java.io.IOException;
import cn.hm.util.SMSMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	UserService service = new UserServiceImpl();
	private static final long serialVersionUID = 1L;

	// loginByUsername
	protected String loginByUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		String msg = service.loginByUsername(user);
		if (msg == null) {
			user = service.getUserByName(username);
			request.getSession().setAttribute("user", user);
			url = "redirect:/GarbageClassification/main/home.jsp";
		} else {
			// 请求转发
			request.setAttribute("msg", msg);
			url = "/user/login.jsp";
		}
		return url;

	}

	protected String loginByPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = null;
		// 1.获取电话号码
		String phone = request.getParameter("phone");
		// 2.获取验证码 
		String vcode = request.getParameter("verificode"); //用户输入验证码
		if (vcode == null || vcode.trim().isEmpty()) {
			request.setAttribute("pmsg", "验证码未输入");
			return "/user/login.jsp";
		}
		System.out.println(vcode);
		String code =request.getSession().getAttribute("lcode").toString();
		System.out.println("code:" + code);
		if (code == null || code.trim().isEmpty()) {
			request.setAttribute("pmsg", "请先获取验证码");
			return "/user/login.jsp";
		}

		String msg = service.loginByPhone(phone, Integer.parseInt(vcode), Integer.parseInt(code));
		User user = new User();
		if (msg == null) {
			request.getSession().setAttribute("user", user);
			url = "redirect:/GarbageClassification/main/home.jsp";
		} else {
			request.setAttribute("msg", msg);
			url = "/user/login.jsp";
		}
		System.out.println(msg);

		return url;
	}

	/*
	 * 检验用户的用户名是否被注册过
	 */
	protected String getUserByUsername(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获得用户名
		String username=request.getParameter("username");
		System.out.println(username);
		String message=service.getUserByUsername(username);
		System.out.println(message);
		response.getWriter().write(message);
		return null;
	}
	/*
	 * 检验手机号是否被注册过
	 */
	protected String getUserByPhone(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获得手机号码
		String phone=request.getParameter("phone");
		System.out.println(phone);
		String message=service.getUserByPhone(phone);
		System.out.println(message);
		response.getWriter().write(message);
		return null;
	}
	/*
	 * 短信发送
	 */
	protected String sendMessage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message=null;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获得手机号码
		String phone=request.getParameter("phone");
		System.out.println(phone);
		//发送短信
		int code=SMSMessage.getCode();
		System.out.println("验证码是："+code);
	 try {
		//验证码放置于session域里面
		request.getSession().setAttribute("code", code);
		message = SMSMessage.SendMessage(phone, code);
	} catch (ServerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClientException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}		
	response.getWriter().write(message);
	return null;
}
	
	/*
	 * 数据的插入
	 */
	protected String addUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//获得用户名
		String username=request.getParameter("username");
		//获得手机号码
		String phone=request.getParameter("phone");
		//获得用户密码
		String password=request.getParameter("password1");
		//获得用户的验证码
		String getCode=request.getParameter("verification");
		String code=request.getSession().getAttribute("code").toString();
		System.out.println(getCode);
		System.out.println(code);
		String message="OK！";
		if(!getCode.equals(code)||code==null||code.trim().isEmpty()) {
			message="验证码不正确！";
			request.setAttribute("messageCode", message);
			return "/user/register.jsp";
		}
		System.out.println(message);
		System.out.println(username);
		//封装数据
		User user=new User();
		user.setUsername(username);		
		user.setPassword(password);
		user.setPhone(phone);
		if(service.addUser(user)) {
			//插入成功,就返回到登陆页面
			return "redirect:/GarbageClassification/user/login.jsp";
		}
		//否则返回到注册页面
		return "redirect:/GarbageClassification/user/register.jsp";
	}
}

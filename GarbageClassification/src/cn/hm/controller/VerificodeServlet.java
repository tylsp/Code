package cn.hm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;

import cn.hm.util.BaseServlet;
import cn.hm.util.SMSMessage;;

@WebServlet("/VerificodeServlet")
public class VerificodeServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	//ajax调用
	protected String getVerifiy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String message= null;
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String phone = request.getParameter("phone");
		int code = SMSMessage.getCode();
		System.out.println("验证码是："+code);
		try {
			//验证码放置于session域里面
			request.getSession().setAttribute("lcode", code);
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
	

}

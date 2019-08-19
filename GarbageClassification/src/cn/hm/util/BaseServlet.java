package cn.hm.util;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset-UTF-8");
		String method = request.getParameter("method");
		if(method == null || method.trim().isEmpty()) {
			//throw new RuntimeException("无法确认您想要调用的方法，请提供method参数！");
		}
		Class<? extends BaseServlet> clazz = this.getClass();
		Method mt = null;
		try {
			mt = clazz.getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			mt.setAccessible(true);
			String value = (String) mt.invoke(this, request, response);
			if(value == null || value.trim().isEmpty()) {
				return;
			}
			int index = value.lastIndexOf(":");
			if(index == -1) {
				request.getRequestDispatcher(value).forward(request, response);
				return;
			}
			String before = value.substring(0, index);
			String after = value.substring(index + 1);
			if(before.equalsIgnoreCase("forward")) {
				request.getRequestDispatcher(after).forward(request, response);
			}else if(before.equalsIgnoreCase("redirect")) {
				response.sendRedirect(after);
			}else {
				throw new RuntimeException("当前版本不支持您要操作！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

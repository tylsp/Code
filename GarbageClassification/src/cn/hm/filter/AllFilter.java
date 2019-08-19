package cn.hm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class AllFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=((HttpServletRequest)request);
		String url=req.getRequestURI();
		//System.out.println(req.getContextPath()+"/css");
		if(!url.startsWith(req.getContextPath()+"/css")
				&&!url.startsWith(req.getContextPath()+"/fonts")
				&&!url.startsWith(req.getContextPath()+"/images")
				&&!url.startsWith(req.getContextPath()+"/js")
				) {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			//System.out.println(url);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

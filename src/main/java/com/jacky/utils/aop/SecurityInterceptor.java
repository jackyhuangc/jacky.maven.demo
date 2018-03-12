/*package com.xujj;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

public class SecurityInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("SecurityInterceptor:" + request.getContextPath() + "," + request.getRequestURI() + ","
				+ request.getMethod());
		HttpSession session = request.getSession();
		if (session.getAttribute("User") == null) {
			System.out.println("AuthorizationException:Î´µÇÂ¼£¡" + request.getMethod());
			if ("POST".equalsIgnoreCase(request.getMethod())) {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.write(JSON.toJSONString(new ReturnResult<String>("001", "Î´µÇÂ¼£¡", "")));
				out.flush();
				out.close();
			} else {
				response.sendRedirect(request.getContextPath() + "/login");
			}
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}
}*/
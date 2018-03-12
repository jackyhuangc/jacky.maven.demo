package jacky.maven.demo;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.HttpRequestHandlerServlet;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * Description Here!
 * 
 * @author Jacky Huang
 * @date 2018-02-04 16:53
 * @since jdk1.8
 */
public class T_Proxy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JDKProxy jdkProxy = new JDKProxy();

		// �൱�ڽӿ�
		UserManager ujdk = (UserManager) jdkProxy.newProxy(new UserManagerImpl());
		ujdk.addUser("123", "12");

		// �൱�ڼ̳�
		UserManager ucglib = (UserManager) new CGLibProxy().createProxyObject(new UserManagerImpl());
		ucglib.addUser("111", "11");

		UserManager u = new UserManager() {

			@Override
			public void addUser(String id, String password) {
				// TODO Auto-generated method stub
				System.out.println("xxxxxxxxxxxx" + id + password);
			}

			@Override
			public void delUser(String id) {
				// TODO Auto-generated method stub

			}

		};

		u.addUser("xxx", "bbb");
	}
}

class TimerFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub

		Servlet x;
		HttpServletRequest x1;
		HttpServlet h1;

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}
}

// HttpServlet��ʵ��Servlet�ӿ�
class HelloServlet extends HttpServlet {

	// HTTPЭ���е��������Ӧ���Ƕ�Ӧ�� HttpServletRequest �� HttpServletResponse �������ӿ�
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("Hello World");
	}
}

// Servlet �淶�ﻹ������һ���ǳ���Ҫ���ҷǳ����õĽӿ��Ǿ��� Filter ��������
class HelloFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("Filter init...");
	}

	// Filter����һ��servlet�������ܲ���һ��response������������request����servlet֮ǰԤ�ȴ���request��Ҳ��������Ӧ�뿪servletʱ����response��
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		System.out.println("���� URI=" + request.getRequestURI());
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("Filter destroy..");
	}
}

interface UserManager {
	void addUser(String id, String password);

	@Autowired()
	void delUser(String id);
}

class UserManagerImpl implements UserManager {

	@Override
	public void addUser(String id, String password) {
		// TODO Auto-generated method stub
		System.out.println("������addUser" + id + "***" + password);
	}

	@Override
	public void delUser(String id) {
		// TODO Auto-generated method stub
		System.out.println("������addUser" + id);
	}
}

// JDK����
class JDKProxy implements InvocationHandler {
	private Object targetObject;

	public Object newProxy(Object targetObject) {
		// TODO Auto-generated method stub
		this.targetObject = targetObject;

		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(),
				this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Object ret = null;

		// ��������߼���advice�����˴�����Ŀ�������ִ��֮ǰ
		ret = method.invoke(targetObject, args);
		// ��������߼���advice�����˴�����Ŀ�������ִ��֮��
		return ret;
	}
}

class CGLibProxy implements MethodInterceptor {
	Object targetObject;

	public Object createProxyObject(Object obj) {
		this.targetObject = obj;
		Enhancer enhaner = new Enhancer();
		enhaner.setSuperclass(obj.getClass());
		enhaner.setCallback(this);

		Object proxyObj = enhaner.create();
		return proxyObj;
	}

	@Override
	public Object intercept(Object targetObject, Method method, Object[] args, MethodProxy arg3) throws Throwable {

		// ��������߼���advice�����˴�����Ŀ�������ִ��֮ǰ
		Object ret = method.invoke(this.targetObject, args);
		// ��������߼���advice�����˴�����Ŀ�������ִ��֮��
		return ret;
	}
}
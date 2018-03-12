package com.jacky.utils.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.jacky.utils.log.SystemLog;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * LogAopAction��־AOP������,�������� @AspectJ ֧�ֺ��� Spring ����������һ���� @Aspect ע�͵�
 * Bean��Spring �����Զ�ʶ��� Bean�������� Bean ��Ϊ���� Bean ����
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:54
 * @since jdk1.8
 */
@Aspect
public class LogAopAction {
	// ��ȡ��ʼʱ��
	private long BEGIN_TIME;

	// ��ȡ����ʱ��
	private long END_TIME;

	// ���屾��logʵ��
	private SystemLog systemLog = new SystemLog();

	/**
	 * ����һ�����������������������ʽ��������һ�㲻��Ҫ����������� ʹ��@Pointcut�����������ʽ
	 * �����ֱ֪ͨ��ʹ�÷����������õ�ǰ���е���ʽ�������������ʹ�ã����ϰ�������
	 * pointcut�����������˼˵�����������˼��Ҳ���Ǻ��е�ʱ�򣬻�����Щִ�е�ᱻʶ��ֻ����ʶ���ˣ�����ִ����Ӧ��Advice��
	 * ÿ���඼ӵ�ж�����ӵ㣬�����ӵ��ǳ������п͹۴��ڵ����� ͨ��execution���ʽ���������Щ����
	 */
	@Pointcut("execution(* com.jacky.mvc..*.*(..))")
	private void controllerAspect() {
	}

	/**
	 * ������ʼִ��
	 */
	@Before("controllerAspect()")
	public void doBefore() {
		BEGIN_TIME = new Date().getTime();
		System.out.println("��ʼ");
	}

	/**
	 * ��������ִ��
	 */
	@After("controllerAspect()")
	public void after() {
		END_TIME = new Date().getTime();
		System.out.println(END_TIME);
		System.out.println("����");
	}

	/**
	 * ��������ִ�к�Ĳ������ڷ������ؽ��֮��֪ͨ��
	 */
	@AfterReturning("controllerAspect()")
	public void doAfter() {

		if (systemLog != null && systemLog.getState() != 0) {
			systemLog.setActionTime(END_TIME - BEGIN_TIME);
			systemLog.setOptTime(new Date(BEGIN_TIME));
			System.out.println(JSON.toJSONString(systemLog));
			System.out.println(">>>>>>>>>>���뵽���ݿ�");
		} else {

			// ����־ע���ǣ��ݲ�����
		}
	}

	/**
	 * �������쳣ʱ�Ĳ���
	 */
	@AfterThrowing("controllerAspect()")
	public void doAfterThrow() {
		System.out.println("����֪ͨ-----------------------------------");
	}

	/**
	 * ����ִ��
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// return pjp.proceed();

		// ��־ʵ�����
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// ���ص�ʵ���࣬���ǵ�ǰ����ִ�е�controller
		Object target = pjp.getTarget();
		// ���صķ������ơ���ǰ����ִ�еķ���
		String methodName = pjp.getSignature().getName();

		// ���صķ�������
		Object[] args = pjp.getArgs();

		// ���صķŲ�������
		Signature sig = pjp.getSignature();

		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("��ע��ֻ�����ڷ���");
		}

		msig = (MethodSignature) sig;

		String[] names = msig.getParameterNames();

		// ��ȡ�����������
		Map<String, Object> mapParams = this.getParams(names, args);

		Class[] parameterTypes = msig.getMethod().getParameterTypes();

		Object object = null;

		Method method = null;
		try {
			method = target.getClass().getMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ����Ƿ��������¼������־
		if (null != method) {
			// �ж��Ƿ�����Զ����ע�⣬˵��һ�������SystemLog�������Լ��Զ����ע��
			if (method.isAnnotationPresent(Log.class)) {
				Log log = method.getAnnotation(Log.class);
				systemLog.setOptModule(log.module());
				systemLog.setOptType(log.methods());
				systemLog.setLoginIP(getIp(request));
				systemLog.setActionUrl(request.getRequestURI());

				try {
					object = pjp.proceed();
					systemLog.setRemark(mapParams.toString());
					systemLog.setState((short) 1);
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					systemLog.setRemark("ִ��ʧ��");
					systemLog.setState((short) -1);
				}
			} else {// û�а���ע��
				object = pjp.proceed();
				systemLog.setRemark("�˲���������ע��");
				systemLog.setState((short) 0);
			}
		} else { // ����Ҫ����ֱ��ִ��
			object = pjp.proceed();
			systemLog.setRemark("����Ҫ����ֱ��ִ��");
			systemLog.setState((short) 0);
		}

		System.out.println("********************");

		return object;
	}

	/**
	 * ��ȡip��ַ
	 * 
	 * @param request
	 * @return
	 */
	private String getIp(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}

	/**
	 * ��ȡ�������ƺ�ֵ
	 * 
	 * @param cls
	 * @param clazzName
	 * @param methodName
	 * @param args
	 * @return
	 * @throws NotFoundException
	 */
	private Map<String, Object> getParams(String[] names, Object[] args) {
		Map<String, Object> map = new HashMap<String, Object>();

		for (int i = 0; i < names.length; i++) {
			map.put(names[i], args[i]);
		}

		return map;
	}
}
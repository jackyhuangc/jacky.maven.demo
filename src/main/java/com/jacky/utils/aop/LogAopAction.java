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
 * LogAopAction日志AOP拦截器,当启动了 @AspectJ 支持后，在 Spring 容器中配置一个带 @Aspect 注释的
 * Bean，Spring 将会自动识别该 Bean，并将该 Bean 作为方面 Bean 处理
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:54
 * @since jdk1.8
 */
@Aspect
public class LogAopAction {
	// 获取开始时间
	private long BEGIN_TIME;

	// 获取结束时间
	private long END_TIME;

	// 定义本次log实体
	private SystemLog systemLog = new SystemLog();

	/**
	 * 定义一个方法，用于声明切入点表达式，方法中一般不需要添加其他代码 使用@Pointcut声明切入点表达式
	 * 后面的通知直接使用方法名来引用当前的切点表达式；如果是其他类使用，加上包名即可
	 * pointcut，从字面的意思说的是切面的意思。也就是横切的时候，会有哪些执行点会被识别。只有先识别了，才能执行相应的Advice。
	 * 每个类都拥有多个连接点，即连接点是程序类中客观存在的事务 通过execution表达式标记拦截哪些方法
	 */
	@Pointcut("execution(* com.jacky.mvc..*.*(..))")
	private void controllerAspect() {
	}

	/**
	 * 方法开始执行
	 */
	@Before("controllerAspect()")
	public void doBefore() {
		BEGIN_TIME = new Date().getTime();
		System.out.println("开始");
	}

	/**
	 * 方法结束执行
	 */
	@After("controllerAspect()")
	public void after() {
		END_TIME = new Date().getTime();
		System.out.println(END_TIME);
		System.out.println("结束");
	}

	/**
	 * 方法结束执行后的操作，在方法返回结果之后通知；
	 */
	@AfterReturning("controllerAspect()")
	public void doAfter() {

		if (systemLog != null && systemLog.getState() != 0) {
			systemLog.setActionTime(END_TIME - BEGIN_TIME);
			systemLog.setOptTime(new Date(BEGIN_TIME));
			System.out.println(JSON.toJSONString(systemLog));
			System.out.println(">>>>>>>>>>存入到数据库");
		} else {

			// 无日志注解标记，暂不拦截
		}
	}

	/**
	 * 方法有异常时的操作
	 */
	@AfterThrowing("controllerAspect()")
	public void doAfterThrow() {
		System.out.println("例外通知-----------------------------------");
	}

	/**
	 * 方法执行
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("controllerAspect()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		// return pjp.proceed();

		// 日志实体对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();

		// 拦截的实体类，就是当前正在执行的controller
		Object target = pjp.getTarget();
		// 拦截的方法名称。当前正在执行的方法
		String methodName = pjp.getSignature().getName();

		// 拦截的方法参数
		Object[] args = pjp.getArgs();

		// 拦截的放参数类型
		Signature sig = pjp.getSignature();

		MethodSignature msig = null;
		if (!(sig instanceof MethodSignature)) {
			throw new IllegalArgumentException("该注解只能用于方法");
		}

		msig = (MethodSignature) sig;

		String[] names = msig.getParameterNames();

		// 获取请求参数内容
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

		// 如果是方法，则记录访问日志
		if (null != method) {
			// 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
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
					systemLog.setRemark("执行失败");
					systemLog.setState((short) -1);
				}
			} else {// 没有包含注解
				object = pjp.proceed();
				systemLog.setRemark("此操作不包含注解");
				systemLog.setState((short) 0);
			}
		} else { // 不需要拦截直接执行
			object = pjp.proceed();
			systemLog.setRemark("不需要拦截直接执行");
			systemLog.setState((short) 0);
		}

		System.out.println("********************");

		return object;
	}

	/**
	 * 获取ip地址
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
	 * 获取参数名称和值
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
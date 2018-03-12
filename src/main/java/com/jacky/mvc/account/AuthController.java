package com.jacky.mvc.account;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jacky.utils.aop.Log;

/**
 * The module of login or logout etc.
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:43
 * @since jdk1.8
 */
@Controller
@RequestMapping("/auth")
public class AuthController {
	// private static Logger logger = Logger.getLogger(AuthController.class);

	/**
	 * 登录页面
	 * 
	 * @param error
	 *            错误代码
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	// @SystemLog(methods = "登录", module = "认证")
	public ModelAndView login(@RequestParam(value = "error", required = false, defaultValue = "0") int error,
			HttpServletRequest request) {

		// 设置SESSION过期为60分钟，这里的单位是秒钟
		// httpSession.setMaxInactiveInterval(60 * 60);

		// ApplicationContext context = new
		// AnnotationConfigApplicationContext(SpringRootConfig.class);
		// SpringBean obj = (SpringBean) context.getBean("springBean");
		// obj.say();
		// System.out.println(obj.getTest1());
		// System.out.println(obj.getTest2());
		// System.out.println(JSON.toJSONString(obj));

		// ApplicationContext context =
		// WebApplicationContextUtils.getWebApplicationContext(request.getServletContext(),"org.springframework.web.servlet.FrameworkServlet.CONTEXT.SpringMVC");
		// ApplicationContext context = new
		// ClassPathXmlApplicationContext("classpath:spring-mvc.xml");

		// Xml配置装配（显示）
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		Course obj = (Course) context.getBean("course_x");
		System.out.println(JSON.toJSONString(obj));
		
		// Java代码装配（显示）
		// ApplicationContext context1 = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		// Course obj1 = (Course) context1.getBean("course");
		// System.out.println(JSON.toJSONString(obj1));

		// 自动装配（隐式）
		ApplicationContext context2 = new AnnotationConfigApplicationContext(AutoConfig.class);
		Course obj2 = (Course) context2.getBean("course_auto");
		System.out.println(JSON.toJSONString(obj2));

		ModelAndView mv = new ModelAndView("login");

		if (error > 0) {
			mv.addObject("error", "You have entered an invalid username or password!");
		} else {
			mv.addObject("error", "");
		}
		return mv;
	}

	/**
	 * 指定未授权页面
	 * 
	 * @return 视图名称
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		return "denied";

	}
}

//// 方法2，Java代码装配
// @Configuration //
//// @Configuration注解表示配置类，类似与用xml配置<bean>的概念，@Configuration也会被ComponentScan扫描到
// class AppConfig {
//
// @Bean
// public Course course() {
// return new Course();
// }
//
// @Bean
// //@Scope("singleton")
// public Module module() {
// return new Module("基于Java代码装配的数据", 222);
// }
// }

// 方法3，自动装配，会扫描所有的Component组件,在XML中<context: componnet-scan
// base-package="soundsystem">
@ComponentScan
class AutoConfig {

}

class Assignment {

	private String test1 = "基于自动装配的数据";

	public String getTest1() {
		return test1;
	}

	public void setTest1(String test1) {
		this.test1 = test1;
	}

	private int test2 = 333;

	public int getTest2() {
		return test2;
	}

	public void setTest2(int test2) {
		this.test2 = test2;
	}

	public Assignment() {
	}

	public Assignment(String test1, int test2) {
		this.test1 = test1;
		this.test2 = test2;
	}
}

@Component // 适用于方法3，注解为组件类，告诉Spring这是一个bean,默认名称为module，便于@Autowired时自动加载。方法2无法找到
class Module {
	public Module() {
		this.assignment = new Assignment();
	}

	public Module(String test1, int test2) {
		this.assignment = new Assignment(test1, test2);
	}

	private Assignment assignment;

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}
}

@Component("course_auto") // 适用于方法3，这个简单的注解表明该类会作为组件类,@ComponentScan才能扫描到并创建bean
class Course {
	public Course() {
		// this.module = new Module();
	}

	private Module module;

	public Module getModule() {
		return module;
	}

	@Autowired // 适用于方法3，将会自动装配合适的bean,
				// 注：通过Java方式配置Bean时不需要此注解，如果要用，确保有此bean（本代码在AppConfig已注册modulebean）
	public void setModule(Module module) {
		this.module = module;
	}
}

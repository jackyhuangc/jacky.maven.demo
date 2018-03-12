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
	 * ��¼ҳ��
	 * 
	 * @param error
	 *            �������
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	// @SystemLog(methods = "��¼", module = "��֤")
	public ModelAndView login(@RequestParam(value = "error", required = false, defaultValue = "0") int error,
			HttpServletRequest request) {

		// ����SESSION����Ϊ60���ӣ�����ĵ�λ������
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

		// Xml����װ�䣨��ʾ��
		WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		Course obj = (Course) context.getBean("course_x");
		System.out.println(JSON.toJSONString(obj));
		
		// Java����װ�䣨��ʾ��
		// ApplicationContext context1 = new
		// AnnotationConfigApplicationContext(AppConfig.class);
		// Course obj1 = (Course) context1.getBean("course");
		// System.out.println(JSON.toJSONString(obj1));

		// �Զ�װ�䣨��ʽ��
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
	 * ָ��δ��Ȩҳ��
	 * 
	 * @return ��ͼ����
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		return "denied";

	}
}

//// ����2��Java����װ��
// @Configuration //
//// @Configurationע���ʾ�����࣬��������xml����<bean>�ĸ��@ConfigurationҲ�ᱻComponentScanɨ�赽
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
// return new Module("����Java����װ�������", 222);
// }
// }

// ����3���Զ�װ�䣬��ɨ�����е�Component���,��XML��<context: componnet-scan
// base-package="soundsystem">
@ComponentScan
class AutoConfig {

}

class Assignment {

	private String test1 = "�����Զ�װ�������";

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

@Component // �����ڷ���3��ע��Ϊ����࣬����Spring����һ��bean,Ĭ������Ϊmodule������@Autowiredʱ�Զ����ء�����2�޷��ҵ�
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

@Component("course_auto") // �����ڷ���3������򵥵�ע������������Ϊ�����,@ComponentScan����ɨ�赽������bean
class Course {
	public Course() {
		// this.module = new Module();
	}

	private Module module;

	public Module getModule() {
		return module;
	}

	@Autowired // �����ڷ���3�������Զ�װ����ʵ�bean,
				// ע��ͨ��Java��ʽ����Beanʱ����Ҫ��ע�⣬���Ҫ�ã�ȷ���д�bean����������AppConfig��ע��modulebean��
	public void setModule(Module module) {
		this.module = module;
	}
}

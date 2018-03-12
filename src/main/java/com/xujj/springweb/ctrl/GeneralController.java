package com.xujj.springweb.ctrl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.jacky.utils.aop.Log;
import com.jacky.mvc.system.SystemInfo;

@Controller
public class GeneralController {

    private static Logger logger = LoggerFactory.getLogger(GeneralController.class);

    String message = "Welcome to Spring MVC!";

    /**
     * 根据请求名称hello调用相应的控制器
     *
     * @param model
     */
    @RequestMapping(value = "index.do")
    public void index(Model model) {
        model.addAttribute("xujj", "Hello World");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
        System.out.println("***************************************************");
    }

    /**
     * 根据请求名称hello调用相应的控制器
     *
     * @param model
     */
    @RequestMapping(value = "index2.do")
    public void index1(Model model) {

        WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();
        SystemInfo s = (SystemInfo) wc.getBean("systemInfo");
        System.out.println(s.getDescription());
        model.addAttribute("xujj", "Hello World YYYYYYYYYYYYYYY");
        logger.info("您正在操作：" + s.getName() + "系统！");
    }

    /**
     * 推荐方式，更为清晰
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @Log(methods = "视图测试", module = "测试模块")
    public ModelAndView showMessage(
            @RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {

        // 指定视图
        ModelAndView mv = new ModelAndView("hellospring");
        // 向视图中添加所要展示或使用的内容，将在页面中使用
        mv.addObject("message", message);
        mv.addObject("name", name);
        return mv;
    }

    @RequestMapping("/hello1")
    public String showMessage1(Model mv,
                               @RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {

        // 指定视图
        // ModelAndView mv = new ModelAndView("hellospring");
        // 向视图中添加所要展示或使用的内容，将在页面中使用
        mv.addAttribute("message", message);
        mv.addAttribute("name", name);

        return "hellospring";// 将渲染并返回hellospring.jsp
    }

/*	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		// 指定视图
		ModelAndView mv = new ModelAndView("login");
		// 向视图中添加所要展示或使用的内容，将在页面中使用
		// mv.addObject("message", message);
		// mv.addObject("name", name);
		return mv;
	}*/


    /**
     * xxxxxxxxx
     * @return xxxxxxxxx
     */
    @RequestMapping(value = "/main/common")
    public String common() {
        return "commonpage";
    }

    @RequestMapping(value = "/main/admin")
    public String admin() {
        return "adminpage";
    }
}
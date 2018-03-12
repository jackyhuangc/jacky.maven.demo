package com.xujj.springweb.ctrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jacky.utils.aop.Log;
import com.xujj.springweb.UserAccount;
import com.xujj.springweb.UserGroup;

@Controller
@RequestMapping("/home")
public class AccountController {

	/**
	 * 推荐方式，更为清晰
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/account.do", method = RequestMethod.GET)
	@Log(methods = "视图测试", module = "测试模块")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {

		// 指定视图
		ModelAndView mv = new ModelAndView("account");
		// 向视图中添加所要展示或使用的内容，将在页面中使用
		mv.addObject("command", new UserAccount());
		mv.addObject("name", name);
		
		List<UserGroup> list=new ArrayList<UserGroup>();
		list.add(new UserGroup(0,"Admin"));
		list.add(new UserGroup(1,"Guest"));
		mv.addObject("groups", list);
		return mv;
	}

	// @ModelAttribute("user") 相当于给account参数取一个别名为user的model, 便于在渲染result页面的时候使用
	@RequestMapping(value = "/addAccount.do", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("user") UserAccount account) {

		/**
		 * @ModelAttribute有两个作用， 一个是取出数据，也就是将数据从请求中取出来封装到controller方法的参数中
		 * 一个是将这个数据放置到Model中，这样你就可以在jsp页面中使用EL表达式取出数据并显示(否则页面中无法通过spring
		 * form标签获取model信息)
		 */
		// System.out.println(JSON.toJSONString(account));
		// model.addAttribute("name", account.getName());
		// model.addAttribute("age", account.getAge());
		// model.addAttribute("id", account.getId());

		return "result";
	}
}
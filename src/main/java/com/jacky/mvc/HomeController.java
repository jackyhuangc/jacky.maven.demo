package com.jacky.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jacky.mvc.entities.Menu;
import com.jacky.mvc.system.SystemInfo;
import com.jacky.utils.CommonTools;
import com.jacky.utils.aop.Log;

/**
 * Home page or main page etc.
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:50
 * @since jdk1.8
 */
@Controller
public class HomeController {

	/**
	 * 首页
	 * 
	 * @param navId
	 *            导航ID
	 * @return 视图
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Log(methods = "首页方法", module = "首页")
	public ModelAndView home(
			@RequestParam(value = "curr_nav", required = false, defaultValue = "1001") String curr_nav) {

		WebApplicationContext wc = ContextLoader.getCurrentWebApplicationContext();
		SystemInfo si = (SystemInfo) wc.getBean("systemInfo");

		// 指定视图
		ModelAndView mv = new ModelAndView("home");
		// 向视图中添加所要展示或使用的内容，将在页面中使用
		mv.addObject("title", si.getName());
		mv.addObject("image", si.getImage());

		List<Menu> menu_all = JSON.parseArray(CommonTools.ReadFromFile("menu.json"), Menu.class);

		List<Menu> menu_nav = CommonTools.deepCopy(menu_all);

		// // 先调用addAll设置menu_nav的长度size，否则menu_nav的size为0，copy会报错
		// Collections.addAll(menu_nav,new Menu[menu_all.size()]);
		//
		// // 怎么还是浅拷贝？？？
		// Collections.copy(menu_nav, menu_all);

		menu_nav = getNav(menu_nav);
		mv.addObject("menu_nav", menu_nav);

		List<Menu> menu_side = getChild(menu_all, curr_nav);

		// 选中菜单
		if (curr_nav.length() > 4) {
			mv.addObject("prnt_nav", curr_nav.substring(0, 4));
		} else {
			mv.addObject("prnt_nav", curr_nav);
		}

		// 选中子菜单
		mv.addObject("curr_nav", curr_nav);

		// 侧边栏菜单
		mv.addObject("menu_side", menu_side);
		return mv;
	}

	private List<Menu> getNav(List<Menu> menu_nav) {
		// List<Menu> menu_side = new ArrayList<Menu>();
		for (int i = menu_nav.size() - 1; i >= 0; i--) {
			Menu menu = menu_nav.get(i);
			if (menu.getType() != 0) {
				menu_nav.remove(i);
			} else if (menu.getChild().size() > 0) {
				menu.setChild(getNav(menu.getChild()));
			}
		}

		return menu_nav;
	}

	private List<Menu> getChild(List<Menu> menu_all, String curr_nav) {
		List<Menu> menu_side = new ArrayList<Menu>();
		for (Menu menu : menu_all) {
			if (menu.getCode().equals(curr_nav)) {
				menu_side = menu.getChild();
			} else if (menu.getChild().size() > 0) {
				menu_side = getChild(menu.getChild(), curr_nav);
			}

			if (menu_side.size() > 0)
				break;
		}

		return menu_side;
	}
}

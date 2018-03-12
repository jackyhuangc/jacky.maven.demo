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
	 * �Ƽ���ʽ����Ϊ����
	 * 
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/account.do", method = RequestMethod.GET)
	@Log(methods = "��ͼ����", module = "����ģ��")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "Spring") String name) {

		// ָ����ͼ
		ModelAndView mv = new ModelAndView("account");
		// ����ͼ�������Ҫչʾ��ʹ�õ����ݣ�����ҳ����ʹ��
		mv.addObject("command", new UserAccount());
		mv.addObject("name", name);
		
		List<UserGroup> list=new ArrayList<UserGroup>();
		list.add(new UserGroup(0,"Admin"));
		list.add(new UserGroup(1,"Guest"));
		mv.addObject("groups", list);
		return mv;
	}

	// @ModelAttribute("user") �൱�ڸ�account����ȡһ������Ϊuser��model, ��������Ⱦresultҳ���ʱ��ʹ��
	@RequestMapping(value = "/addAccount.do", method = RequestMethod.POST)
	public String addStudent(@ModelAttribute("user") UserAccount account) {

		/**
		 * @ModelAttribute���������ã� һ����ȡ�����ݣ�Ҳ���ǽ����ݴ�������ȡ������װ��controller�����Ĳ�����
		 * һ���ǽ�������ݷ��õ�Model�У�������Ϳ�����jspҳ����ʹ��EL���ʽȡ�����ݲ���ʾ(����ҳ�����޷�ͨ��spring
		 * form��ǩ��ȡmodel��Ϣ)
		 */
		// System.out.println(JSON.toJSONString(account));
		// model.addAttribute("name", account.getName());
		// model.addAttribute("age", account.getAge());
		// model.addAttribute("id", account.getId());

		return "result";
	}
}
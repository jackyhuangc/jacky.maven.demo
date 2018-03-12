package com.xujj.springweb.ctrl;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("auth1")
@SessionAttributes({ "error" })
public class LoginLogoutController {

	protected static Logger logger = Logger.getLogger("controller");

	/**
	 * 指向登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(value = "error", required = false) boolean error, ModelMap model,
			HttpSession httpSession) {

		// 设置SESSION过期为60分钟
		// httpSession.setMaxInactiveInterval(60 * 60);
		logger.debug("Received request to show login page");
		httpSession.setAttribute("TEST", "ABC");
		if (error == true) {
			// Assign an error message
			model.put("error", "You have entered an invalid username or password!");
		} else {
			model.put("error", "");
		}

		return "loginpage";

	}

	/**
	 * 指定无访问额权限页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {

		logger.debug("Received request to show denied page");

		return "deniedpage";

	}
}
package com.jacky.mvc.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * The module of session etc.
 * 
 * @author Jacky Huang
 * @date 2018-01-28 17:45
 * @since jdk1.8
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

	@RequestMapping("/session")
	public ModelAndView sessionError() {
		ModelAndView mv = new ModelAndView("error/session_error");
		return mv;
	}
	
	@RequestMapping("/unauthorized")
	public ModelAndView unauthorizedError() {
		ModelAndView mv = new ModelAndView("error/unauthorized_error");
		return mv;
	}
}
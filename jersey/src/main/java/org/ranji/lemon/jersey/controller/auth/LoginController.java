package org.ranji.lemon.jersey.controller.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.ranji.lemon.jersey.model.auth.User;
import org.ranji.lemon.jersey.service.auth.prototype.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;

@Controller 
public class LoginController {
	
	@Autowired
	private IUserService userService;
	
	
	@RequestMapping("/test1")
	@ResponseBody
	public String test1(){
		System.out.println(SecurityUtils.getSubject().getSession().getId());
		return "test1";
	}
	
	@RequestMapping("/test2")
	@ResponseBody
	public String test2(){
		List<User> users = userService.findAll();
		for (User user : users) {
			System.out.println(user);
		}
		return "test2";
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("key", "hello world");
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView login(User user, String captcha, HttpSession session,HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView();
		
		String kaptchaExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
//--System.out.println(kaptchaExpected);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword());
		try{
			subject.login(token);
System.out.println(subject.getSession().getId());
System.out.println(session.getId());
			mv.setViewName("redirect:/hello");
		} catch (AuthenticationException e){
			mv.addObject("message", "login errors");
			mv.setViewName("redirect:/backend/login");
		} 
		return mv;
	}
}

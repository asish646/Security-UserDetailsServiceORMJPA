package com.hsbc.security.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hsbc.security.entity.User;
import com.hsbc.security.service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes({"userName"})
public class UserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/register")
	public String showForm() {
		return "UserRegister";
	}
	
	@PostMapping("/save")
	public String saveUser(@ModelAttribute User user, Model model) {
		
		Integer savedUser = service.saveUser(user);
		model.addAttribute("msg", "User with id '"+savedUser+"' is registered sussessfully!");
		return "UserRegister";
	}
	
	//Legacy Style
//	@GetMapping("/setUp")
//	public String setUP(Principal principal, HttpSession session) {
//		
//		String userName = principal.getName();
//		User userByEmail = service.findByUserEmail(userName);
//		session.setAttribute("userName", userByEmail.getUserName());
//		return "redirect:common";
//	}

	//SpringBoot Style
	@GetMapping("/setUp")
	public String setUP(Principal principal, Model model) {
		
		String userName = principal.getName();
		User userByEmail = service.findByUserEmail(userName);
		model.addAttribute("userName", userByEmail.getUserName());
		return "redirect:common";
	}
	
	@GetMapping("/login")
	public String loginPage() {
		return "LoginForm";
	}
	
	@GetMapping("/all")
	public String welcomeAll() {
		return "WelcomePage";
	}
	
	@GetMapping("/common")
	public String home() {
		return "HomePage";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "AdminPage";
	}
	
	@GetMapping("/employee")
	public String employee() {
		return "EmployeePage";
	}
	
	@GetMapping("/denied")
	public String denied() {
		return "DeniedPage";
	}
}

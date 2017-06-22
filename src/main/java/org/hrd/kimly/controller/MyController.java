package org.hrd.kimly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

	@RequestMapping("/home")
	public String homePage(){
		
		return "home";
	}
	
	@RequestMapping("/user")
	public String userPage(){
		return "user";
	}
	
}

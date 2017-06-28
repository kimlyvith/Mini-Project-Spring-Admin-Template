package org.hrd.kimly.controller;

import java.util.ArrayList;
import java.util.List;


import org.hrd.kimly.model.User;
import org.hrd.kimly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserRestController {

	private UserService userService;
	
	/**
	 * ..........??????
	 * @param userService
	 */
	@Autowired
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * ????????????????
	 * @return
	 */
	@RequestMapping("/show")
	@ResponseBody
	public List<User> getUser(){
		return userService.getUser();
	}
	
	@RequestMapping("/add-user")
	public String addUser(User user, ModelMap map){
		map.addAttribute("KEY",userService.getUser());
		map.addAttribute("USER", new User());
		return "/user/add-user";
	}
	
}

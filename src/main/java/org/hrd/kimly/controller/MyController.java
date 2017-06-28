package org.hrd.kimly.controller;

import java.sql.Timestamp;

import org.hrd.kimly.model.User;

import org.hrd.kimly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import  org.springframework.ui.ModelMap;



@Controller
public class MyController {
	private String userHash;
	
	@Autowired
	private UserService userService;

	@RequestMapping("/home")
	public String homePage(){
		
		return "home";
	}
	
	@RequestMapping("/user/user-list")
	public String addUser(Model model){
		model.addAttribute("USER",userService.getUser());
		return "/user/user-list";
	}
	
	@RequestMapping(value="/mysave",method=RequestMethod.POST)
	public String addUser1(@ModelAttribute User user,Model map){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		user.setCreated_date(timestamp);
		System.out.println(user.getUsername());
		userService.save(user);
		return "redirect:/user/user-list";
	}
	
	@RequestMapping(value="/remove-user/{user_hash}")
	public String Remove(@PathVariable("user_hash") String user_hash,Model model){
		
		userService.deleteByUserHash(user_hash);
		model.addAttribute("KEY",userService.getUser());
		return "redirect:/user/user-list";
	}
	
	@RequestMapping(value="/user-detail/{user_hash}")
	public String userDetail(@PathVariable("user_hash") String user_hash,Model model){
		model.addAttribute("UDETAIL",userService.selectByUserHash(user_hash));
		return "/user/user-detail";
	}
	
	
	@RequestMapping("/update/{user_hash}")
	public String updateUser(@PathVariable("user_hash") String user_hash,ModelMap model) {
		userHash = user_hash;
		model.addAttribute("USER", userService.getUser1(user_hash));
		model.addAttribute("user_hash",user_hash);
		return "user/update-user";
	}
	
	
	@RequestMapping("/update")
	public String update(User user) {
		user.setUser_hash(userHash);
		userService.updateByUserHash(user);
				
		return "redirect:user/user-list";
	}
	
	@RequestMapping("/user/dashboard")
	public String indexPage(ModelMap model) {
		model.addAttribute("NUMOFUSER", userService.getUser().size());
		return "/user/dashboard";
	}
	
}

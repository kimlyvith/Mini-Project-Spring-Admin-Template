package org.hrd.kimly.controller;

import org.hrd.kimly.model.User;
import org.hrd.kimly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class MyController {
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
		
//		String uuid = UUID.randomUUID().toString();
//		
//		user.setUser_hash(uuid);
		
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
	
	
//	
//	@RequestMapping("/user-list")
//	public String test(Model m){
//		m.addAttribute("TEST","Hello");
//		return "user-list";
//	}
//	@RequestMapping("/user")
//	public String userPage(){
//		return "user";
//	}
	
}

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
	
	
	/**
	 * 
	 * @return
	 */
	/*@RequestMapping("/save")
	public boolean save(){
		return userService.save(new User(6, "dara", "dararith@gmail.com", "M","0987336363","0"));
	}*/
//	private String userHash;
	
	@RequestMapping("/add-user")
	public String addUser(User user, ModelMap map){
		map.addAttribute("KEY",userService.getUser());
		map.addAttribute("USER", new User());
		return "/user/add-user";
	}


	
	
	/**
	 * 
	 * @param userHash
	 * @return
	 */
	@RequestMapping("/delete/{user_hash}")
	public boolean delete(@PathVariable("user_hash") String userHash){
		return userService.deleteByUserHash(userHash);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("/update")
	public boolean update(){
		User user = new User();
		user.setUsername("DADA");
		user.setUser_hash("fgasdfg-fasd-fads-fasd");
		return userService.updateByUserHash(user);
	}
	
	@RequestMapping("/save-batch")
	public boolean saveBatch(){
		ArrayList<User> users = new ArrayList<User>();
		User user = new User();
		user.setUsername("DADA");
		users.add(user);
		user = new User();
		user.setUsername("TADA");
		users.add(user);
		return userService.saveBatch(users);
	}
}

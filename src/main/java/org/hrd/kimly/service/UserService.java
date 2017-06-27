package org.hrd.kimly.service;

import java.util.List;

import org.hrd.kimly.model.User;

public interface UserService {

	public List<User> getUser();
	public boolean save(User user);
	public boolean deleteByUserHash(String userHash);
	public User selectByUserHash(String userHash);
	public boolean updateByUserHash(User user);
	public boolean saveBatch(List<User> users);
	
}

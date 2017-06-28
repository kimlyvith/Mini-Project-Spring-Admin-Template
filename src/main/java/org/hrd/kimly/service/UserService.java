package org.hrd.kimly.service;

import java.util.List;

import org.hrd.kimly.model.User;

public interface UserService {

	public List<User> getUser();
	public boolean save(User user);
	public boolean deleteByUserHash(String userHash);
	public User selectByUserHash(String userHash);
	public boolean updateUser(User user);
	public User getUser(String user_hash);
	public User getUser1(String user_hash);
	public boolean updateByUserHash(User user);
	
}

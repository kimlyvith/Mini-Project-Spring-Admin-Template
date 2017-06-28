package org.hrd.kimly.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hrd.kimly.model.User;
import org.hrd.kimly.repositories.UserRepository;
import org.hrd.kimly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

	private UserRepository userRepository;
	@Autowired
	private UserServiceImp(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	@Override
	public List<User> getUser() {
		// TODO Auto-generated method stub
		return userRepository.getUser();
	}
	@Override
	public boolean save(User user) {
		// TODO: save user to database
		String userHash = UUID.randomUUID().toString();
		user.setUser_hash(userHash);
		boolean status = userRepository.save(user);
		if(status){
			System.out.println("USER ID : " + user.getId());
			System.out.println("User has been inserted!");
		} else {
			System.out.println("User has not been inserted!.");
		}
		return status;
	}

	@Override
	public boolean deleteByUserHash(String userHash) {
		// TODO: delete user from database by userHash
		boolean status = userRepository.delete(userHash);
		if (status) {
			System.out.println("User has been deleted!");
		} else {
			System.out.println("User has not been deleted!.");
		}
		return status;
	}

	@Override
	public boolean updateByUserHash(User user) {
		// TODO: update user from database by userHash
		boolean status = userRepository.updateUser(user);
		if (status) {
			System.out.println("User has been updated!");
		} else {
			System.out.println("User has not been updated!");
		}
		return status;
	}


	public User selectByUserHash(String userHash) {
		
		ArrayList<User> users = (ArrayList<User>)getUser();
		for(User u : users){
			if(u.getUser_hash().equals(userHash)){
				return u;
			}
		}
		
		return null;
	}

	@Override
	public boolean updateUser(User user) {
		
		return userRepository.updateUser(user);
	}
	
	@Override
	public User getUser1(String user_hash) {
		return userRepository.getUser1(user_hash);
	}

	@Override
	public User getUser(String user_hash) {
		// TODO Auto-generated method stub
		return null;
	}

	
}

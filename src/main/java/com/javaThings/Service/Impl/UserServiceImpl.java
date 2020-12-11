package com.javaThings.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaThings.Bean.User;
import com.javaThings.Repository.UserRepo;
import com.javaThings.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepository;
	
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}

	@Override
	public User findOne(int id) {
		java.util.Optional<User> optional=userRepository.findById(id);
		if(!optional.isPresent())
			return null;
		
		return optional.get();
	}

	@Override
	public void Delete(int id) {
		
		userRepository.deleteById(id);
	}
	public User getUserByName(String name)
	{
		
		return userRepository.findByName(name);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
		
	}

	@Override
	public User update(User user) {
		return userRepository.save(user);
	}
}

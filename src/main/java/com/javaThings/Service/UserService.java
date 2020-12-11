package com.javaThings.Service;

import java.util.List;

import com.javaThings.Bean.User;

public interface UserService {
	public List<User> findAll();
	public User findOne(int id);
	public void Delete(int id);
	public User save(User user);
	public User update(User user);
	public User getUserByName(String name);
	
}

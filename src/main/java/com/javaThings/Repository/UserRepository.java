package com.javaThings.Repository;

import java.util.List;

import com.javaThings.Bean.User;

public interface UserRepository {

	public List<User> findAll();
	public User findOne(int id);
	public User Delete(int id);
	public User save(User user);
	public User update(User user);
}
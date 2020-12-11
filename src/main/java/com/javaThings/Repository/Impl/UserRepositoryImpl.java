package com.javaThings.Repository.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaThings.Bean.User;
import com.javaThings.Repository.UserRepository;

@SuppressWarnings("deprecation")
@Repository
public class UserRepositoryImpl implements UserRepository {

	private static List<User> UserRepo= new ArrayList<User>();
	private static Integer usersCount = 3;
	static {
		UserRepo.add(new User("Vijay", "Bijapur", new Date(1989, 9, 24)));
		UserRepo.add(new User("Swarupa", "Solapur", new Date(1994, 4, 23)));
		UserRepo.add(new User("Aarna", "Bijapur", new Date()));
		
	}
	@Override
	public List<User> findAll() {
		
		return UserRepo;
	}

	@Override
	public User findOne(int Id) {
		for(User user:UserRepo)
		{
			if(user.getId()==Id)
				return user;
		}
		return null;
	}

	@Override
	public User Delete(int id) {
		Iterator<User> iterator=UserRepo.iterator();
		while(iterator.hasNext())
		{
			User user=(User)iterator.next();
			if(user.getId()==id) {
				iterator.remove();
				return user;
			}
				
		}
		return null;
	}

	@Override
	public User save(User user) {
		if(user.getId()==null|| user.getId()==0)
			user.setId(++usersCount);
		UserRepo.add(user);
		return user;
		
	}

	@Override
	public User update(User user) {
		int index=-1;
		for(User userold:UserRepo)
		{
			if(userold.getId()==user.getId()) 
				index=UserRepo.indexOf(userold);
			
		}
		
		if(index==-1) 
			return null;
		else {
			UserRepo.set(index, user);
			return user;
		}

	}

}
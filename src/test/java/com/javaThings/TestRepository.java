package com.javaThings;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import com.javaThings.Bean.Post;
import com.javaThings.Bean.User;
import com.javaThings.Repository.UserRepo;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TestRepository {
	
	@Autowired
	TestEntityManager entityManager;
	
	@Autowired
	UserRepo repo;
	
	@Test
	public void FindByName()
	{
		User user=new User( "vijay", "ajay", new Date());
		entityManager.persist(user);
		/*FLUSH
	This operation will cause DML statements (insert/update/delete etc) to be executed to the database but the current transaction will not be committed.
	That means flush() will not make current changes visible to other EntityManager instances or other external database clients; 
	that will only happen at the transaction commit. In other words flush() operation will only flush the current memory cache from EntityManager to the database session.
		 * 
		 */
		entityManager.flush();
		Optional<User> user2User=repo.findById(user.getId());
		User user2=user2User.get();
		assertThat(user2.getName()).isEqualTo(user.getName());
	}
}

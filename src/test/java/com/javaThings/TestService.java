package com.javaThings;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.net.MediaType;
import com.javaThings.Bean.User;
import com.javaThings.Repository.UserRepo;
import com.javaThings.Service.UserService;
import com.javaThings.Service.Impl.UserServiceImpl;

@RunWith(SpringRunner.class)
public class TestService {
	
	@TestConfiguration
	static class CreateAnObjectOfServiceImpleCLass
	{
		@Bean
		public UserServiceImpl getUserServiceImpl()
		{
			return new UserServiceImpl();
		}
	}
	@Autowired
	UserService service;
	
	@MockBean
	UserRepo userRepository;
	
	@org.junit.Before
	public void SetUp()
	{
		User user=new User( "vijay", "ajay", new Date());
		Mockito.when(userRepository.findByName(user.getName())).thenReturn(user);
	}
	
	@Test
	public void TestServe()
	{
		User user=service.getUserByName("vijay");
		assertThat(user.getName()).isEqualTo("vijay");
	}
}

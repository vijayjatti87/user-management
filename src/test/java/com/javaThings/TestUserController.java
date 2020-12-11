package com.javaThings;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaThings.Bean.Post;
import com.javaThings.Bean.User;
import com.javaThings.Controller.UserResource;
import com.javaThings.Repository.PostRepostitory;
import com.javaThings.Repository.UserRepo;
import com.javaThings.Service.UserService;
import com.javaThings.Service.Impl.UserServiceImpl;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
public class TestUserController {
	
	@MockBean
	UserService service;
	
	@MockBean
	PostRepostitory postrepo;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void TestUserResource() throws Exception
	{
		User user=new User( "vijay", "ajay", new Date());
		List<User> users=new ArrayList<User>();
		users.add(user);
		given(service.findAll()).willReturn(users);
		mockMvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is(user.getName())));
		
	}
	
	@Test
	public void testPost() throws Exception
	{
		
		String content="{\"name\":\"ja\",\"address\":\"ajay\"}";
		mockMvc.perform(post("/user").content(content)
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
	}
	public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.javaThings.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
/*import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;*/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import com.javaThings.Bean.Post;
import com.javaThings.Bean.User;
import com.javaThings.Exception.UserNotFoundException;
import com.javaThings.Repository.PostRepostitory;
import com.javaThings.Service.UserService;

@RestController
public class UserResource {

	@Autowired
	private UserService service;
	
	@Autowired
	private PostRepostitory postRepo; 
	
	@GetMapping("/user")
	public List<User> getAllUsers()
	{
		return service.findAll();
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable int id)
	{ 
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("Id-"+id+" Not found");
		return user;
	}
	
	@GetMapping("/user/{id}/Posts")
	public List<com.javaThings.Bean.Post> getPostsByUserId(@PathVariable int id)
	{ 
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("Id-"+id+" Not found");
		
		return user.getPosts();
	}
	
	@PostMapping("/user/{id}/Posts")
	public ResponseEntity<Object> AddPostsByUserId(@PathVariable int id,@RequestBody Post post)
	{ 
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("Id-"+id+" Not found");
		post.setUser(user);
		Post post2=postRepo.save(post);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{post_id}").buildAndExpand(post2.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/user/{id}/Posts/{post_id}")
	public com.javaThings.Bean.Post getPostsByIdByUserId(@PathVariable int id,@PathVariable int post_id)
	{ 
		User user=service.findOne(id);
		if(user==null)
			throw new UserNotFoundException("User Id-"+id+" Not found");
		
		Optional<Post> post=postRepo.findById(post_id);
		Post post2=post.get();
		if(!post.isPresent())
			throw new UserNotFoundException("Post Id-"+post2.getId()+" Not found");
		
		User user2=post2.getUser();
		if(!user.equals(user2))
			throw new UserNotFoundException("User or Post Id Not found");
		return post2;
	}
	
	/*
	 * @GetMapping("/user/{id}") public Resource<User> getOneUser(@PathVariable
	 * Integer id) { User user=(User)service.findOne(id);
	 * 
	 * if(user==null ) throw new UserNotFoundException("Id-"+id+" not found");
	 * Resource<User> resource=new Resource<User>(user); ControllerLinkBuilder
	 * builder=linkTo(methodOn(this.getClass()).getAllUsers());
	 * resource.add(builder.withRel("all-users")); return resource; }
	 */
	
	@DeleteMapping("user/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable Integer id)
	{
		service.Delete(id);
		return new ResponseEntity<Object>("deleted the User with Id "+id,HttpStatus.OK);
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> CreateUser(@Valid @RequestBody User user)
	{
		User newUser=service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<Object> UpdateUser(@PathVariable int id,@Valid @RequestBody User user)
	{
		user.setId(id);
		User newUser=service.update(user);
		
		if(newUser!=null)
			return new ResponseEntity<Object>("Updated the User with Id "+newUser.getId(),HttpStatus.OK);
		else
			throw new UserNotFoundException("Id-"+user.getId()+" not found");
	}
}
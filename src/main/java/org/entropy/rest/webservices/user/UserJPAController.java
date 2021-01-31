package org.entropy.rest.webservices.user;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.entropy.rest.webservices.post.Posts;
import org.entropy.rest.webservices.post.PostsRepository;
import org.entropy.rest.webservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PostsRepository postsRepository;
	/**
	//Query String
	@GetMapping(path="/hello")
	public String hello(@RequestParam(name="msg",required=false,defaultValue="world") String msg) {
		return "Hello "+msg;
	}
	*/
	
	
	@GetMapping(path="/jpa/users")
	public List<Users> getAllUser() {
		List<Users> users= userRepository.findAll();
		return users;
	}
	
	//getOneUser
	@GetMapping(path="/jpa/users/{id}")
	public EntityModel<Optional<Users>> getOneUser(@PathVariable Integer id) {
		Optional<Users> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User with id: "+id+" not present");
		EntityModel<Optional<Users>> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkto = linkTo(methodOn(this.getClass()).getAllUser());
		
		resource.add(linkto.withRel("All-users"));
		
		
		return resource;
	}
	
	//saveUser
	@PostMapping(path="/jpa/users")
	public Users saveUser(@Valid @RequestBody Users user) {
		Users savedUser = userRepository.save(user);
		
		return savedUser;
	}
	
	//deletUser
	
	@DeleteMapping(path="/jpa/users/{id}")
	public  void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
		
	}
	
	@GetMapping(path="/jpa/users/{id}/posts")
	public List<Posts> getPosts(@PathVariable Integer id){
		Optional<Users> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User with id: "+id+" not present");
		return userOptional.get().getPosts();	 
	}
	
	@PostMapping(path="/jpa/users/{id}/posts")
	public Posts savePosts(@PathVariable Integer id,@RequestBody Posts post) {
		Optional<Users> userOptional=userRepository.findById(id);
		if(!userOptional.isPresent())
			throw new UserNotFoundException("User with id: "+id+" not present");
		Users user=userOptional.get();
		post.setUser(user);
		Posts savedPosts=postsRepository.save(post);
		
		return savedPosts;
	}
}

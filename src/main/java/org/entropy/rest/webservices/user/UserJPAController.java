package org.entropy.rest.webservices.user;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.entropy.rest.webservices.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJPAController {
	
	@Autowired
	UserRepository userRepository;
	
	/**
	//Query String
	@GetMapping(path="/hello")
	public String hello(@RequestParam(name="msg",required=false,defaultValue="world") String msg) {
		return "Hello "+msg;
	}
	*/
	
	
	@GetMapping(path="/jpa/users")
	public List<User> getAllUser() {
		return userRepository.findAll();
	}
	
	//getOneUser
	@GetMapping(path="/jpa/users/{id}")
	public EntityModel<Optional<User>> getOneUser(@PathVariable Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("User with id: "+id+" not present");
		EntityModel<Optional<User>> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkto = linkTo(methodOn(this.getClass()).getAllUser());
		
		resource.add(linkto.withRel("All-users"));
		return resource;
	}
	
	//saveUser
	@PostMapping(path="/jpa/users")
	public User saveUser(@Valid @RequestBody User user) {
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	//deletUser
	
	@DeleteMapping(path="/jpa/users/{id}")
	public  void deleteUser(@PathVariable Integer id) {
		userRepository.deleteById(id);
		
	}
}

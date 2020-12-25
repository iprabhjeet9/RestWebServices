package org.entropy.rest.webservices.user;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	//getAllUser
	@GetMapping(path="/users")
	public List<User> getAllUser() {
		return userDAO.findAll();
	}
	
	//getOneUser
	@GetMapping(path="/users/{id}")
	public EntityModel<User> getOneUser(@PathVariable Integer id) {
		User user = userDAO.findOne(id);
		if (user==null)
			throw new UserNotFoundException("User with id: "+id+" not present");
		EntityModel<User> resource = EntityModel.of(user);
		
		WebMvcLinkBuilder linkto = linkTo(methodOn(this.getClass()).getAllUser());
		
		resource.add(linkto.withRel("All-users"));
		return resource;
	}
	
	//saveUser
	@PostMapping(path="/users")
	public User saveUser(@Valid @RequestBody User user) {
		User savedUser = userDAO.saveOne(user);
		return savedUser;
	}
	
	//deletUser
	
	@DeleteMapping(path="/users/{id}")
	public User deleteUser(@PathVariable Integer id) {
		User user=userDAO.deleteOne(id);
		if(user == null)
			throw new UserNotFoundException("User with id: "+id+" not present");
		return user;
	}
}

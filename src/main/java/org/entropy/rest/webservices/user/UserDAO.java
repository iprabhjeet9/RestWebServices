package org.entropy.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	private static List<User> users = new ArrayList<>();
	
	private static int usersCount=3;
	
	static {
		users.add(new User(1,"Prabh",new Date()));
		users.add(new User(2,"John",new Date()));
		users.add(new User(3,"Max",new Date()));		
	}
	
	// findAll
	public List<User> findAll(){
		return users;
	}
	
	// findOne
	public User findOne(Integer id) {
		for(User user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	// deleteOne
	
	public User deleteOne(Integer id) {
		Iterator<User> iter= users.iterator();
		while(iter.hasNext()) {
			User user = iter.next();
			if(user.getId()==id) {
				iter.remove();
				return user;
			}
			
		}
		return null;
	}
	// saveOne
	
	public User saveOne(User user) {
		if(user.getId()==null)
			user.setId(++UserDAO.usersCount);
		users.add(user);
		return user;
	}

}

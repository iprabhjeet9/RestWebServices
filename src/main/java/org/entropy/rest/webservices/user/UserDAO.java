package org.entropy.rest.webservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDAO {
	private static List<Users> users = new ArrayList<>();
	
	private static int usersCount=3;
	
	static {
		users.add(new Users(1,"Prabh",new Date()));
		users.add(new Users(2,"John",new Date()));
		users.add(new Users(3,"Max",new Date()));		
	}
	
	// findAll
	public List<Users> findAll(){
		return users;
	}
	
	// findOne
	public Users findOne(Integer id) {
		for(Users user:users) {
			if(user.getId()==id) {
				return user;
			}
		}
		return null;
	}
	// deleteOne
	
	public Users deleteOne(Integer id) {
		Iterator<Users> iter= users.iterator();
		while(iter.hasNext()) {
			Users user = iter.next();
			if(user.getId()==id) {
				iter.remove();
				return user;
			}
			
		}
		return null;
	}
	// saveOne
	
	public Users saveOne(Users user) {
		if(user.getId()==null)
			user.setId(++UserDAO.usersCount);
		users.add(user);
		return user;
	}

}

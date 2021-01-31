package org.entropy.rest.webservices.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.entropy.rest.webservices.user.Users;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Posts {
	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Users user;
	
	public Posts() {
		
	}
	
	public Posts(Integer id, String description, Users user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	
	
	
}

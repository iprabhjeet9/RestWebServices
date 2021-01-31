package org.entropy.rest.webservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.entropy.rest.webservices.post.Posts;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Users {
	
	@Id
	@GeneratedValue
	private Integer id;
	@NotNull
	@Size(min=2)
	private String name;
	
	@Past
	@JsonIgnore
	private Date dob;
	
	@OneToMany(mappedBy = "user")
	private List<Posts> posts; 
	
	public Users() {
	}
	
	public Users(Integer id, String name, Date dob) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
}

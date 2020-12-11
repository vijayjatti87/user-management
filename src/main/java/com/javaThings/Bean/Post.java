package com.javaThings.Bean;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	
	@javax.persistence.Id
	@GeneratedValue
	private Integer Id;
	private String Description;
	@ManyToOne(cascade = {CascadeType.MERGE},fetch=FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	public Post() {
		super();
	
	}
	public Post(Integer id, String description, User user) {
		super();
		Id = id;
		Description = description;
		this.user = user;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}

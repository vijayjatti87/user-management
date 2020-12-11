package com.javaThings.Bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="User Entity Description")
//@JsonIgnoreProperties(value= {"Name","Address"})
@Entity
public class User {
	@javax.persistence.Id
	@GeneratedValue
	private Integer id;
public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
@Size(min=2,message="The name is too small")
@ApiModelProperty(notes="name should be atleast 2 chars")
private String name;
private String Address;
@OneToMany(cascade = {CascadeType.MERGE},mappedBy="user")
private List<Post> posts;
@Past
@ApiModelProperty(notes="Birthdate <current date")
private Date birthDate;

public User() {
	super();
}

public User(Integer Id,String name, String address, Date birthDate) {
	super();
	this.id=Id;
	this.name = name;
	Address = address;
	this.birthDate = birthDate;
	
}

public User(String name, String address, Date birthDate) {
	super();
	this.name = name;
	Address = address;
	this.birthDate = birthDate;
	
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return Address;
}
public void setAddress(String address) {
	Address = address;
}
public Date getBirthDate() {
	return birthDate;
}
public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((Address == null) ? 0 : Address.hashCode());
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	User other = (User) obj;
	if (Address == null) {
		if (other.Address != null)
			return false;
	} else if (!Address.equals(other.Address))
		return false;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (birthDate == null) {
		if (other.birthDate != null)
			return false;
	} else if (!birthDate.equals(other.birthDate))
		return false;
	
	return true;
}

}

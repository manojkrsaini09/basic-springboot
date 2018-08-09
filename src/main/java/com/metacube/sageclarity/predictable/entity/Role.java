package com.metacube.sageclarity.predictable.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "role")
public class Role extends BaseEntity implements GrantedAuthority, Serializable{

   @Column
   private String role;
   
   @JsonIgnore
   @ManyToMany(mappedBy = "roles")
   private List<User> users = new ArrayList<>();
	
   public Role() { }
   
   public Role(String role) {
	   this.role = role;
   }
   
	@Override
	public String getAuthority() {
		return this.role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}

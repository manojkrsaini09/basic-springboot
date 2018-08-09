package com.metacube.sageclarity.predictable.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


@Entity(name = "user")
public class User extends BaseEntity implements Serializable{

	 @Column
	  private String name;

	  @Column(name="username",unique = true, nullable = false)
	  private String username;

	  @Column
	  private String password;

	  @Column
	  private boolean enabled;

	  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	  private List<Role> roles = new ArrayList<>();

	  public User() {}

	  public User(String name, String userName, String password, boolean enabled) {
	    super();
	    this.name = name;
	    this.username = userName;
	    this.password = password;
	    this.enabled = enabled;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  public String getPassword() {
	    return password;
	  }

	  public void setPassword(String password) {
	    this.password = password;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public boolean isEnabled() {
	    return enabled;
	  }

	  public void setEnabled(boolean enabled) {
	    this.enabled = enabled;
	  }

	  public List<Role> getRoles() {
	    return roles;
	  }

	  public void setRoles(List<Role> roles) {
	    this.roles = roles;
	  }

}

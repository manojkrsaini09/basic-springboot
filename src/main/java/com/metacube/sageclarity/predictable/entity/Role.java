package com.metacube.sageclarity.predictable.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.metacube.sageclarity.predictable.enums.UserRoleEnum;
import com.metacube.sageclarity.predictable.vo.RoleVO;
import org.springframework.security.core.GrantedAuthority;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "role")
public class Role extends BaseEntity<String> implements GrantedAuthority, Serializable{

	@Column
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	@JsonIgnore
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<>();

	public Role() { }

	public Role(RoleVO roleVO) {
		if(roleVO.getId() != null){
			this.setId(roleVO.getId());
		}
		this.role= UserRoleEnum.getUserRoleFromStringValue(roleVO.getRole());
	}

	public Role(Long id,UserRoleEnum role) {
		this.setId(id);
		this.role= role;
	}

	@Override
	public String getAuthority() {
		return this.role.getValue();
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public UserRoleEnum getRole() {
		return role;
	}

	public void setRole(UserRoleEnum role) {
		this.role = role;
	}
}

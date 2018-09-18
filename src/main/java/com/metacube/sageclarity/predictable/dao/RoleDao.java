package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.Role;

import java.util.List;

public interface RoleDao {
	public Role saveUserRole(Role role);
	public List<Role> getAll();
	public Role getById(Long id);
	public List<Role> saveUserRoles(List<Role> roles);
}

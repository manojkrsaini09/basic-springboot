package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.Role;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

import java.util.List;

public interface RoleService {
	public Role saveRole(Role role) throws ApplicationLevelException;
	public List<Role> getAllRole() throws ApplicationLevelException;
	public List<Role> saveRoles(List<Role> roles) throws ApplicationLevelException;
}

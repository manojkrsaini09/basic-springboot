package com.metacube.sageclarity.predictable.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metacube.sageclarity.predictable.dao.RoleDao;
import com.metacube.sageclarity.predictable.dao.jpa.JpaRoleDao;
import com.metacube.sageclarity.predictable.entity.Role;

import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao{

	@Autowired
	private JpaRoleDao jpaRoleDao;
	
	@Override
	public Role saveUserRole(Role role) {
		return jpaRoleDao.save(role);
	}

	@Override
	public List<Role> getAll() {
		return jpaRoleDao.findAll();
	}

	@Override
	public Role getById(Long id){
		return jpaRoleDao.getOne(id);
	}

	@Override
	public List<Role> saveUserRoles(List<Role> roles) {
		return jpaRoleDao.saveAll(roles);
	}

}

package com.metacube.sageclarity.predictable.service.impl;

import com.metacube.sageclarity.predictable.dao.RoleDao;
import com.metacube.sageclarity.predictable.dao.UserDao;
import com.metacube.sageclarity.predictable.entity.Role;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.service.RoleService;
import com.metacube.sageclarity.predictable.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
	private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Override
	public Role saveRole(Role role) throws ApplicationLevelException {
		if(role == null){
			String message = "Null Role Entity to create.";
			logger.error(message);
			throw new InvalidParamException(message);
		}
		try {
			return roleDao.saveUserRole(role);
		}catch (Exception e){
			String message = "Db exception while creating Role. "+ e.getMessage() ;
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}

	@Override
	public List<Role> getAllRole() throws ApplicationLevelException {

		try {
			List<Role> roles = roleDao.getAll();
			return roles;
		}catch (Exception e){
			String message = "Db exception while finding all user roles." + e.getMessage();
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}

	@Override
	public List<Role> saveRoles(List<Role> roles) throws ApplicationLevelException {
		if(roles == null){
			String message = "Null Role List to create.";
			logger.error(message);
			throw new InvalidParamException(message);
		}
		try {
			return roleDao.saveUserRoles(roles);
		}catch (Exception e){
			String message = "Db exception while creating Roles. "+ e.getMessage() ;
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}

}

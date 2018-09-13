package com.metacube.sageclarity.predictable.service.impl;

import com.metacube.sageclarity.predictable.dao.RoleDao;
import com.metacube.sageclarity.predictable.entity.Role;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metacube.sageclarity.predictable.dao.UserDao;
import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Override
	public User saveUser(User user) throws ApplicationLevelException {
		if(user == null){
			String message = "Null Entity to create.";
			logger.error(message);
			throw new InvalidParamException(message);
		}
		try {
			List<Role> roles = user.getRoles();
			List<Role> tempRoles = new ArrayList<>();
			for(Role role:roles){
				if(role.getId()!=null){
					tempRoles.add(roleDao.getById(role.getId()));
				}else{
					tempRoles.add(role);
				}
			}
			user.getRoles().clear();
			user.setRoles(tempRoles);
			return userDao.saveUser(user);
		}catch (Exception e){
			String message = "Db exception while creating User. "+ e.getMessage() ;
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}

	@Override
	public User getByUserName(String userName) throws ApplicationLevelException {
		if(StringUtils.isBlank(userName)){
			String message= "User Name is null";
			logger.error(message);
			throw new InvalidParamException(message);
		}
		try {
			User user = userDao.getUserByUserName(userName);
			return user;
		}catch (Exception e){
			String message = "Db exception while finding user by unser name: "+ userName + ". " + e.getMessage();
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}

	@Override
	public List<User> getAllUsers() throws ApplicationLevelException {
		try {
			List<User> users = userDao.getAll();
			return users;
		}catch (Exception e){
			String message = "Db exception while fetching all users " + e.getMessage();
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}

	@Override
	public User getById(Long id) throws ApplicationLevelException {
		if(id == null){
			String message= "user id is null";
			logger.error(message);
			throw new InvalidParamException(message);
		}
		try {
			User user = userDao.getById(id);
			return user;
		}catch (Exception e){
			String message = "Db exception while finding user by id: "+ id + ". " + e.getMessage();
			logger.error(message, e);
			throw new ApplicationLevelException(message, e);
		}
	}
}

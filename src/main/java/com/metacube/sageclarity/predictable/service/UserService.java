package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

import java.util.List;

public interface UserService {
	public User saveUser(User user) throws ApplicationLevelException;
	public User getByUserName(String userName) throws ApplicationLevelException;
	public List<User> getAllUsers() throws ApplicationLevelException;
	public User getById(Long id) throws ApplicationLevelException;
}

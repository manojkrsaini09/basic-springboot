package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.User;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

public interface UserService {
	public User saveUser(User user) throws ApplicationLevelException;
	public User getByUserName(String userName) throws ApplicationLevelException;
}

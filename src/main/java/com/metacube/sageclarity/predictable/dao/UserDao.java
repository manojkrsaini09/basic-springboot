package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.User;

public interface UserDao {
	public User saveUser(User user);
	public User getUserByUserName(String userName);
}
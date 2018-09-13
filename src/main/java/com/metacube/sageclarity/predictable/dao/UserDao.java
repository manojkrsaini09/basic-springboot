package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.User;

import java.util.List;

public interface UserDao {
	public User saveUser(User user);
	public User getUserByUserName(String userName);
	public List<User> getAll();
	public User getById(Long id);
}

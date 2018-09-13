package com.metacube.sageclarity.predictable.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.metacube.sageclarity.predictable.dao.UserDao;
import com.metacube.sageclarity.predictable.dao.jpa.JpaUserDao;
import com.metacube.sageclarity.predictable.entity.User;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao{

	@Autowired
	private JpaUserDao jpaUserDao;

	@Override
	public User saveUser(User user) {
		return jpaUserDao.save(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		return jpaUserDao.findByUsername(userName);
	}

	@Override
	public List<User> getAll() {
		return jpaUserDao.findAll();
	}

	@Override
	public User getById(Long id) {
		return jpaUserDao.getOne(id);
	}
}

package com.metacube.sageclarity.predictable.dao.jpa;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.metacube.sageclarity.predictable.entity.User;

@Transactional
public interface JpaUserDao extends JpaRepository<User, Long>{
	public User findByUsername(String name);
}

package com.metacube.sageclarity.predictable.dao.jpa;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metacube.sageclarity.predictable.entity.Role;

@Transactional
public interface JpaRoleDao extends JpaRepository<Role, Long>{

}

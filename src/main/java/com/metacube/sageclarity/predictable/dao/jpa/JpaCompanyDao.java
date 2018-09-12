package com.metacube.sageclarity.predictable.dao.jpa;

import com.metacube.sageclarity.predictable.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface JpaCompanyDao extends JpaRepository<Company,Long> {
}

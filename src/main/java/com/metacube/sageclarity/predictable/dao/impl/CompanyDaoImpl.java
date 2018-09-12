package com.metacube.sageclarity.predictable.dao.impl;

import com.metacube.sageclarity.predictable.dao.CompanyDao;
import com.metacube.sageclarity.predictable.dao.jpa.JpaCompanyDao;
import com.metacube.sageclarity.predictable.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDaoImpl implements CompanyDao {
    @Autowired
    private JpaCompanyDao jpaCompanyDao;

    @Override
    public Company save(Company company) {
        return jpaCompanyDao.save(company);
    }

    @Override
    public List<Company> getAll() {
        return jpaCompanyDao.findAll();
    }

    @Override
    public Company getById(Long id) {
        return jpaCompanyDao.getOne(id);
    }
}

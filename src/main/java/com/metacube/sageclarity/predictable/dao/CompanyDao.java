package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.Company;

import java.util.List;

public interface CompanyDao {
    public Company save(Company company);
    public List<Company> getAll();
    public Company getById(Long id);
}

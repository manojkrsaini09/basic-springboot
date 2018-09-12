package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

import java.util.List;

public interface CompanyService {
    public Company getById(Long id) throws ApplicationLevelException;
    public Company save(Company company) throws ApplicationLevelException;
    public List<Company> getAll() throws ApplicationLevelException;
}

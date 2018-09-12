package com.metacube.sageclarity.predictable.service.impl;

import com.metacube.sageclarity.predictable.dao.CompanyDao;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);
    @Autowired
    private CompanyDao companyDao;

    @Override
    public Company getById(Long id) throws ApplicationLevelException {
        if(id == null){
            String message= "Company id is null";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try {
            Company company = companyDao.getById(id);
            return company;
        }catch (Exception e){
            String message = "Db exception while finding company by id: "+ id + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public Company save(Company company) throws ApplicationLevelException {
        if(company == null){
            String message= "Null object";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try{
             company = companyDao.save(company);
            return company;
        }catch (Exception e){
            String message = "Db exception while saving company with name "+ company.getName() + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public List<Company> getAll() throws ApplicationLevelException {
        try{
            List<Company> companies = companyDao.getAll();
            return companies;
        }catch(Exception e){
            String message = "Db exception while fetching all companies :"+ e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }
}

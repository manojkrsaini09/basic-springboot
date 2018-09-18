package com.metacube.sageclarity.predictable.service.impl;

import com.metacube.sageclarity.predictable.dao.ProductionScheduleMasterDao;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.ProductionScheduleMaster;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.service.ProductionScheduleMasterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductionScheduleMasterServiceImpl implements ProductionScheduleMasterService {

    private static final Logger logger = LoggerFactory.getLogger(ProductionScheduleMasterServiceImpl.class);
    @Autowired
    private ProductionScheduleMasterDao productionScheduleMasterDao;

    @Override
    public ProductionScheduleMaster getById(Long id) throws ApplicationLevelException {
        if(id == null){
            String message= "schedule id is null";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try {
            ProductionScheduleMaster productionSchedule = productionScheduleMasterDao.getById(id);
            return productionSchedule;
        }catch (Exception e){
            String message = "Db exception while finding schedule by id: "+ id + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public ProductionScheduleMaster save(ProductionScheduleMaster productionSchedule) throws ApplicationLevelException {
        if(productionSchedule == null){
            String message= "Null object";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try{
            productionSchedule = productionScheduleMasterDao.save(productionSchedule);
            return productionSchedule;
        }catch (Exception e){
            String message = "Db exception while saving schedule with name "+ productionSchedule.getName() + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public List<ProductionScheduleMaster> getAll() throws ApplicationLevelException {
        try{
            List<ProductionScheduleMaster> productionSchedule = productionScheduleMasterDao.getAll();
            return productionSchedule;
        }catch(Exception e){
            String message = "Db exception while fetching all schedule :"+ e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public Boolean deleteSchedule(ProductionScheduleMaster schedule) throws ApplicationLevelException {
        if(schedule == null){
            String message= "Null object";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try{
           // schedule.setDeleted(true);
            schedule = productionScheduleMasterDao.save(schedule);
            return true;
        }catch (Exception e){
            String message = "Db exception while deleting schedule with id "+ schedule.getId() + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public List<ProductionScheduleMaster> getAllByCompanyId(Long companyId) throws ApplicationLevelException {
        try{
            return productionScheduleMasterDao.getByCompany(new Company(companyId));
        }catch(Exception e) {
            String message = "Db exception while fetching  Schedule for company id " + companyId + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }
}

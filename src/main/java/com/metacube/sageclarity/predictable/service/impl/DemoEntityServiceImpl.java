package com.metacube.sageclarity.predictable.service.impl;

import com.metacube.sageclarity.predictable.dao.DemoEntityDao;
import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.service.DemoEntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoEntityServiceImpl implements DemoEntityService {
    private static final Logger logger = LoggerFactory.getLogger(DemoEntityServiceImpl.class);

    @Autowired
    private DemoEntityDao demoEntityDao;

    @Override
    public DemoEntity saveDemoEntity(DemoEntity obj) throws ApplicationLevelException {
        if(obj == null){
            String message= "Null Entity to create.";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try {
            return demoEntityDao.save(obj);
        }catch (Exception e){
            String message = "Db exception while creating Demo entity. "+ e.getMessage() ;
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public DemoEntity getDemoEntityById(Long id) throws ApplicationLevelException {
        if(id == null){
            String message= "Demo entity id is null";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try {
            DemoEntity demoEntity = demoEntityDao.getById(id);
            return demoEntity;
        }catch (Exception e){
            String message = "Db exception while finding demo entity for id: "+ id + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

}

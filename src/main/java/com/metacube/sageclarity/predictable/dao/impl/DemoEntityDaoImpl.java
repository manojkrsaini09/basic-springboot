package com.metacube.sageclarity.predictable.dao.impl;

import com.metacube.sageclarity.predictable.dao.DemoEntityDao;
import com.metacube.sageclarity.predictable.dao.jpa.JpaDemoEntityDao;
import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.enums.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DemoEntityDaoImpl implements DemoEntityDao {

    @Autowired
    private JpaDemoEntityDao jpaDemoEntityDao;

    @Override
    public DemoEntity save(DemoEntity obj) {
        return jpaDemoEntityDao.save(obj);
    }

    @Override
    public DemoEntity getById(Long id) {
        return jpaDemoEntityDao.getOne(id);
    }

    @Override
    public List<DemoEntity> getAll() {
        return jpaDemoEntityDao.findAll();
    }

    @Override
    public List<DemoEntity> getByName(String name) {
        return jpaDemoEntityDao.findByName(name);
    }

    @Override
    public List<DemoEntity> findByObjectType(ObjectType type) {return jpaDemoEntityDao.findByType(type);}
}

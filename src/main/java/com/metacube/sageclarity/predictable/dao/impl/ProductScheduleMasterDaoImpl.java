package com.metacube.sageclarity.predictable.dao.impl;

import com.metacube.sageclarity.predictable.dao.ProductionScheduleMasterDao;
import com.metacube.sageclarity.predictable.dao.jpa.JpaProductionScheduleMasterDao;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.ProductionScheduleMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductScheduleMasterDaoImpl implements ProductionScheduleMasterDao {
    @Autowired
    private JpaProductionScheduleMasterDao jpaProductionScheduleMasterDao;

    @Override
    public ProductionScheduleMaster save(ProductionScheduleMaster productScheduleMaster) {
        return jpaProductionScheduleMasterDao.save(productScheduleMaster);
    }

    @Override
    public List<ProductionScheduleMaster> getAll() {
        return jpaProductionScheduleMasterDao.findAll();
    }

    @Override
    public ProductionScheduleMaster getById(Long id) {
        return jpaProductionScheduleMasterDao.getOne(id);
    }

    @Override
    public List<ProductionScheduleMaster> getByCompany(Company company) {
        return jpaProductionScheduleMasterDao.findByCompany(company);
    }
}

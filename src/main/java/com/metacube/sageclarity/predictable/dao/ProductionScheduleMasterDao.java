package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;
import com.metacube.sageclarity.predictable.entity.ProductionScheduleMaster;

import java.util.List;

public interface ProductionScheduleMasterDao {
    public ProductionScheduleMaster save(ProductionScheduleMaster product);
    public List<ProductionScheduleMaster> getAll();
    public ProductionScheduleMaster getById(Long id);
    public List<ProductionScheduleMaster> getByCompany(Company company);
}

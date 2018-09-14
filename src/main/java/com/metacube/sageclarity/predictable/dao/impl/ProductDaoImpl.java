package com.metacube.sageclarity.predictable.dao.impl;

import com.metacube.sageclarity.predictable.dao.ProductDao;
import com.metacube.sageclarity.predictable.dao.jpa.JpaProductDao;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl implements ProductDao {
    @Autowired
    private JpaProductDao jpaProductDao;

    @Override
    public Product save(Product product) {
        return jpaProductDao.save(product);
    }

    @Override
    public List<Product> getAll() {
        return jpaProductDao.findAll();
    }

    @Override
    public Product getById(Long id) {
        return jpaProductDao.getOne(id);
    }

    @Override
    public List<Product> getByCompany(Company company) {
        return jpaProductDao.findByCompany(company);
    }
}

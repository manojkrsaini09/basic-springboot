package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;

import java.util.List;

public interface ProductDao {
    public Product save(Product product);
    public List<Product> getAll();
    public Product getById(Long id);
    public List<Product> getByCompany(Company company);
}

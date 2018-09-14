package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

import java.util.List;

public interface ProductService {
    public Product getById(Long id) throws ApplicationLevelException;
    public Product save(Product product) throws ApplicationLevelException;
    public List<Product> getAll() throws ApplicationLevelException;
    public Boolean deleteProduct(Product product) throws ApplicationLevelException;
    public List<Product> getAllByCompanyId(Long companyId) throws ApplicationLevelException;
}

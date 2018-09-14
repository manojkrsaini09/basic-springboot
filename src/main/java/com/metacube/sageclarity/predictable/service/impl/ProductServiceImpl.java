package com.metacube.sageclarity.predictable.service.impl;

import com.metacube.sageclarity.predictable.dao.CompanyDao;
import com.metacube.sageclarity.predictable.dao.ProductDao;
import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;
import com.metacube.sageclarity.predictable.exception.InvalidParamException;
import com.metacube.sageclarity.predictable.service.CompanyService;
import com.metacube.sageclarity.predictable.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getById(Long id) throws ApplicationLevelException {
        if(id == null){
            String message= "product id is null";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try {
            Product product = productDao.getById(id);
            return product;
        }catch (Exception e){
            String message = "Db exception while finding product by id: "+ id + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public Product save(Product product) throws ApplicationLevelException {
        if(product == null){
            String message= "Null object";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try{
            product = productDao.save(product);
            return product;
        }catch (Exception e){
            String message = "Db exception while saving product with name "+ product.getName() + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public List<Product> getAll() throws ApplicationLevelException {
        try{
            List<Product> products = productDao.getAll();
            return products;
        }catch(Exception e){
            String message = "Db exception while fetching all products :"+ e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public Boolean deleteProduct(Product product) throws ApplicationLevelException {
        if(product == null){
            String message= "Null object";
            logger.error(message);
            throw new InvalidParamException(message);
        }
        try{
            product.setDeleted(true);
            product = productDao.save(product);
            return true;
        }catch (Exception e){
            String message = "Db exception while deleting product with id "+ product.getId() + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }

    @Override
    public List<Product> getAllByCompanyId(Long companyId) throws ApplicationLevelException {
        try{
            return productDao.getByCompany(new Company(companyId));
        }catch(Exception e) {
            String message = "Db exception while fetching  product for company id " + companyId + ". " + e.getMessage();
            logger.error(message, e);
            throw new ApplicationLevelException(message, e);
        }
    }
}

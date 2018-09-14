package com.metacube.sageclarity.predictable.dao.jpa;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface JpaProductDao extends JpaRepository<Product,Long> {
    public List<Product> findByCompany(Company company);
}

package com.metacube.sageclarity.predictable.dao.jpa;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.ProductionScheduleMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface JpaProductionScheduleMasterDao extends JpaRepository<ProductionScheduleMaster,Long> {
    public List<ProductionScheduleMaster> findByCompany(Company company);
}

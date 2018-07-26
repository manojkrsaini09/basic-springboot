package com.metacube.sageclarity.predictable.dao.jpa;

import com.metacube.sageclarity.predictable.entity.DemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface JpaDemoEntityDao extends JpaRepository<DemoEntity,Long> {
    public List<DemoEntity> findByName(String name);
}

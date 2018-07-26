package com.metacube.sageclarity.predictable.dao.jpa;

import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.enums.ObjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface JpaDemoEntityDao extends JpaRepository<DemoEntity,Long> {
    public List<DemoEntity> findByName(String name);
    public List<DemoEntity> findByType(ObjectType type);
}

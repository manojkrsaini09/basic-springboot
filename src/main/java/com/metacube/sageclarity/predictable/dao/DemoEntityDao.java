package com.metacube.sageclarity.predictable.dao;

import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.enums.ObjectType;

import java.util.List;

public interface DemoEntityDao {
    public DemoEntity save(DemoEntity obj);
    public DemoEntity getById(Long id);
    public List<DemoEntity> getAll();
    public List<DemoEntity> getByName(String name);
    public List<DemoEntity> findByObjectType(ObjectType type);
}

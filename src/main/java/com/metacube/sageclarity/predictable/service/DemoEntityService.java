package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.enums.ObjectType;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

import java.util.List;

public interface DemoEntityService {
    public DemoEntity saveDemoEntity(DemoEntity obj) throws ApplicationLevelException;
    public DemoEntity getDemoEntityById(Long id) throws ApplicationLevelException;
    public List<DemoEntity> getByObjectType(String objectType) throws ApplicationLevelException;
}

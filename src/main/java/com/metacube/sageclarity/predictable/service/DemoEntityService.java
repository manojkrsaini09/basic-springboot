package com.metacube.sageclarity.predictable.service;

import com.metacube.sageclarity.predictable.entity.DemoEntity;
import com.metacube.sageclarity.predictable.exception.ApplicationLevelException;

public interface DemoEntityService {
    public DemoEntity saveDemoEntity(DemoEntity obj) throws ApplicationLevelException;
    public DemoEntity getDemoEntityById(Long id) throws ApplicationLevelException;
}

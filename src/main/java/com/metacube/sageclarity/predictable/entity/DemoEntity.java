package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.enums.ObjectType;
import com.metacube.sageclarity.predictable.vo.DemoEntityVO;

import javax.persistence.*;

@Entity
@Table(name = "demo_entity")
public class DemoEntity extends BaseEntity<String> {

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private ObjectType type;

    public DemoEntity(){}

    public DemoEntity(DemoEntityVO vo){
        if(vo.getId()!=null)
        super.setId(vo.getId());
        this.name = vo.getName();
        this.type = ObjectType.getObjectTypeFromStringValue(vo.getType());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObjectType getType() {
        return type;
    }

    public void setType(ObjectType type) {
        this.type = type;
    }
}

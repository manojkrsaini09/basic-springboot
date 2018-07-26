package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.DemoEntity;

public class DemoEntityVO {
    private Long id;
    private String name;
    private String type;

    public DemoEntityVO(){}

    public DemoEntityVO(DemoEntity demoEntity){
        this.id = demoEntity.getId();
        this.name = demoEntity.getName();
        if(demoEntity.getType()!=null){
            this.type = demoEntity.getType().getValue();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

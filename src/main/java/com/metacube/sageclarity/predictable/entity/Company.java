package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.vo.CompanyVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "company")
public class Company extends BaseEntity implements Serializable {
    @Column
    private String name;

    public Company() {
    }

    public Company(CompanyVO companyVO) {
        if(companyVO.getId()!=null){
            super.setId(companyVO.getId());
        }

        this.name = companyVO.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

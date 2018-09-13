package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.vo.CompanyVO;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "company")
public class Company extends BaseEntity implements Serializable {
    @Column
    private String name;

    @Column
    private String location;

    public Company() {
    }

    public Company(CompanyVO companyVO) {
        if(companyVO.getId()!=null){
            super.setId(companyVO.getId());
        }

        this.name = companyVO.getName();
        this.location = companyVO.getLocation();
    }

    public Company(Long id){
        super.setId(id);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

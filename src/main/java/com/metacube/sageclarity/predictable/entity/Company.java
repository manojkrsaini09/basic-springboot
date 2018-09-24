package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.vo.CompanyVO;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity(name = "Organization")
public class Company extends BaseEntity<String> implements Serializable {
    @Column
    private String name;

    @Column
    private String location;

    @Column
    private Boolean isActive=true;

    public Company() {
    }

    public Company(CompanyVO companyVO) {
        if(companyVO.getId()!=null){
            super.setId(companyVO.getId());
        }
        this.isActive = companyVO.getActive();
        this.name = companyVO.getName();
        this.location = companyVO.getLocation();
    }

    public Company(CompanyVO companyVO,Company company) {
        BeanUtils.copyProperties(company,this);
        if(companyVO.getId()!=null){
            super.setId(companyVO.getId());
        }
        this.isActive = companyVO.getActive();
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

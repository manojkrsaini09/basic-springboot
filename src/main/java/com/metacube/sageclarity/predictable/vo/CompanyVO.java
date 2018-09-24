package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.Company;

public class CompanyVO {
    private Long id;
    private String name;
    private String  location;
    private Boolean isActive;
    public CompanyVO() {
    }

    public CompanyVO(Company company) {
        if(company.getId()!=null){
            this.id = company.getId();
        }
        this.isActive = company.getActive();
        this.name = company.getName();
        this.location = company.getLocation();
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

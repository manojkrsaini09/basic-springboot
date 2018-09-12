package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.Company;

public class CompanyVO {
    private Long id;
    private String name;

    public CompanyVO() {
    }

    public CompanyVO(Company company) {
        if(company.getId()!=null){
            this.id = company.getId();
        }
        this.name = company.getName();
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
}

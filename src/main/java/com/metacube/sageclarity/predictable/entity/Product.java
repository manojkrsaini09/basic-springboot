package com.metacube.sageclarity.predictable.entity;

import com.metacube.sageclarity.predictable.vo.ProductVO;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Product")
public class Product extends  BaseEntity implements Serializable {
    @Column
    private String name;

    @Column
    private String attributes;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public Product() {
    }

    public Product(ProductVO productVO) {
        if(productVO.getId()!=null){
            super.setId(productVO.getId());
        }
        this.name = productVO.getName();
        this.attributes = productVO.getAttributes();
        this.company = new Company(productVO.getCompanyVO());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

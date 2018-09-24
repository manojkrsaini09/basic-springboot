package com.metacube.sageclarity.predictable.entity;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.metacube.sageclarity.predictable.vo.ProductVO;
import org.hibernate.annotations.Where;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Product")
@Where(clause = "is_deleted ='false'")
public class Product extends  BaseEntity<String> implements Serializable {
    @Column
    private String name;

    @Column
    private String attributes;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Company company;

    @Column
    private Boolean isDeleted=false;

    @Column
    private Boolean isActive;

    public Product() {
    }

    public Product(ProductVO productVO) {
        if(productVO.getId()!=null){
            super.setId(productVO.getId());
        }
        this.isActive = productVO.getActive();
        this.name = productVO.getName();
        this.attributes = productVO.getAttributes();
        if(productVO.getCompanyVO()!=null){
            this.company = new Company(productVO.getCompanyVO());
        }else if(productVO.getCompanyId()!=null){
            this.company = new Company(productVO.getCompanyId());
        }

    }

    public Product(ProductVO productVO,Product product) {
        BeanUtils.copyProperties(product,this);
        if(productVO.getId()!=null){
            super.setId(productVO.getId());
        }
        this.name = productVO.getName();
        this.isActive = productVO.getActive();
        this.attributes = productVO.getAttributes();
        if(productVO.getCompanyVO()!=null){
            this.company = new Company(productVO.getCompanyVO());
        }else if(productVO.getCompanyId()!=null){
            this.company = new Company(productVO.getCompanyId());
        }

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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

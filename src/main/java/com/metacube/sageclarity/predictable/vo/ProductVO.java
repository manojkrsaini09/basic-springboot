package com.metacube.sageclarity.predictable.vo;

import com.metacube.sageclarity.predictable.entity.Company;
import com.metacube.sageclarity.predictable.entity.Product;

public class ProductVO {
    private Long id;
    private String name;
    private String  attributes;
    private CompanyVO companyVO;
    private Long companyId;
    private Boolean isActive;
    public ProductVO() {
    }

    public ProductVO(Product product) {
        if(product.getId()!=null){
            this.id = product.getId();
        }
        this.isActive = product.getActive();
        this.name = product.getName();
        this.attributes = product.getAttributes();
        if(product.getCompany()!= null){
            this.companyVO = new CompanyVO(product.getCompany());
            this.companyId = product.getCompany().getId();
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

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public CompanyVO getCompanyVO() {
        return companyVO;
    }

    public void setCompanyVO(CompanyVO companyVO) {
        this.companyVO = companyVO;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

package com.metacube.sageclarity.predictable.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductionRun extends BaseEntity<String> implements Serializable {
    @Column
    private String  productionRunName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Company company;

    @Column
    private Boolean isActive;

    public String getProductionRunName() {
        return productionRunName;
    }

    public void setProductionRunName(String productionRunName) {
        this.productionRunName = productionRunName;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}

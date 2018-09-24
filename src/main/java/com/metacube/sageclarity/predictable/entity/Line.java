package com.metacube.sageclarity.predictable.entity;

import javax.persistence.*;

@Entity
public class Line extends BaseEntity<String>  {
    @Column
    private String lineName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Company company;

    @Column
    private Boolean isActive;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
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

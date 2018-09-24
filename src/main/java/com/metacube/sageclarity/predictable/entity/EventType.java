package com.metacube.sageclarity.predictable.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class EventType extends BaseEntity<String> implements Serializable {
    @Column
    private String eventTypeName;

    @Column
    private String reasonLevel1;

    @Column
    private String reasonLevel2;

    @Column
    private String reasonLevel3;

    @Column
    private String reasonLevel4;

    @Column
    private String reasonLevel5;

    @Column
    private String  eventTypeConstraints;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "org_id", referencedColumnName = "id")
    private Company company;

    public String getEventTypeName() {
        return eventTypeName;
    }

    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }

    public String getReasonLevel1() {
        return reasonLevel1;
    }

    public void setReasonLevel1(String reasonLevel1) {
        this.reasonLevel1 = reasonLevel1;
    }

    public String getReasonLevel2() {
        return reasonLevel2;
    }

    public void setReasonLevel2(String reasonLevel2) {
        this.reasonLevel2 = reasonLevel2;
    }

    public String getReasonLevel3() {
        return reasonLevel3;
    }

    public void setReasonLevel3(String reasonLevel3) {
        this.reasonLevel3 = reasonLevel3;
    }

    public String getReasonLevel4() {
        return reasonLevel4;
    }

    public void setReasonLevel4(String reasonLevel4) {
        this.reasonLevel4 = reasonLevel4;
    }

    public String getReasonLevel5() {
        return reasonLevel5;
    }

    public void setReasonLevel5(String reasonLevel5) {
        this.reasonLevel5 = reasonLevel5;
    }

    public String getEventTypeConstraints() {
        return eventTypeConstraints;
    }

    public void setEventTypeConstraints(String eventTypeConstraints) {
        this.eventTypeConstraints = eventTypeConstraints;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

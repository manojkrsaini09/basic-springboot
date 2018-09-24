package com.metacube.sageclarity.predictable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class PredictionParameters extends BaseEntity<String> implements Serializable {
    @Column
    private String parameter_name;

    public String getParameter_name() {
        return parameter_name;
    }

    public void setParameter_name(String parameter_name) {
        this.parameter_name = parameter_name;
    }
}

package com.metacube.sageclarity.predictable.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class MetricConfiguration extends BaseEntity<String> implements Serializable{

    @Column
    private String metricName;

    @Column
    private String metricDimensions;

    @Column
    private String metricComputation;

    public String getMetricName() {
        return metricName;
    }

    public void setMetricName(String metricName) {
        this.metricName = metricName;
    }

    public String getMetricDimensions() {
        return metricDimensions;
    }

    public void setMetricDimensions(String metricDimensions) {
        this.metricDimensions = metricDimensions;
    }

    public String getMetricComputation() {
        return metricComputation;
    }

    public void setMetricComputation(String metricComputation) {
        this.metricComputation = metricComputation;
    }
}

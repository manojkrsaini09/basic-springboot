package com.metacube.sageclarity.predictable.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class ProductionSchedulePredictions extends BaseEntity<String> implements Serializable {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private ProductionScheduleData order;

    @Column
    private int predictedPuration;

    @Column
    private LocalDateTime predictedStart;

    public ProductionScheduleData getOrder() {
        return order;
    }

    public void setOrder(ProductionScheduleData order) {
        this.order = order;
    }

    public int getPredictedPuration() {
        return predictedPuration;
    }

    public void setPredictedPuration(int predictedPuration) {
        this.predictedPuration = predictedPuration;
    }

    public LocalDateTime getPredictedStart() {
        return predictedStart;
    }

    public void setPredictedStart(LocalDateTime predictedStart) {
        this.predictedStart = predictedStart;
    }
}

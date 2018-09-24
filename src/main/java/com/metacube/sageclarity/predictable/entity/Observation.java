package com.metacube.sageclarity.predictable.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Observation extends BaseEntity<String> implements Serializable {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    private Line line;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_run_id", referencedColumnName = "id")
    private ProductionRun productRun;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_type_id", referencedColumnName = "id")
    private EventType eventType;

    @Column
    private int duration;

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductionRun getProductRun() {
        return productRun;
    }

    public void setProductRun(ProductionRun productRun) {
        this.productRun = productRun;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

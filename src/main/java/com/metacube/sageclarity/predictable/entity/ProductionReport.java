/*
package com.metacube.sageclarity.predictable.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
public class ProductionReport extends BaseEntity<String> implements Serializable {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "line_id", referencedColumnName = "id")
    private Line line;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}
*/

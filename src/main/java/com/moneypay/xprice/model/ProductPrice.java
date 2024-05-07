package com.moneypay.xprice.model;

import com.moneypay.xprice.constant.AppConstants;
import com.moneypay.xprice.model.base.Amount;
import com.moneypay.xprice.model.base.BaseUpdatableEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import java.io.Serial;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(schema = AppConstants.Db.XPRICE_SCHEMA_NAME, name = "product_price",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"product_id", "seller"})})
@Getter
@Setter
@Accessors(chain = true)
@SuperBuilder
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
public class ProductPrice extends BaseUpdatableEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "seller")
    private String seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Embedded
    private Amount amount;

    @Version
    @Column(name = "version", nullable = false)
    private Integer version;
}

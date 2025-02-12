package br.com.manage.store.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "tb_sales_product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "amount")
    private int amount;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "price_with_discount")
    private BigDecimal priceWithDiscount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_product_sales_id", nullable = false)
    private SalesEntity salesEntity;

}

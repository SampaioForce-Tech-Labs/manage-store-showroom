package br.com.manage.store.domain.entity;

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

    @Column(name = "name")
    private String name;

    @Column(name = "price_unit")
    private BigDecimal unitPrice;

    @Column(name = "price_total")
    private BigDecimal totalPrice;

    @Column(name = "amount")
    private int amount;

    @Column(name = "price_with_discount")
    private BigDecimal priceWithDiscount;

    @ManyToOne
    @JoinColumn(name = "sales_product_sales_id", referencedColumnName = "id", nullable = false)
    private SalesEntity salesEntity;

}

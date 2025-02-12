package br.com.manage.store.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_sales")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_items")
    private int totalItems;

    @Column(name = "discount_total_sum")
    private BigDecimal discountTotalSum;

    @Column(name = "total_sum")
    private BigDecimal totalSum;

    @Column(name = "total_with_discount")
    private BigDecimal totalWithDiscount;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    @OneToMany(mappedBy = "salesEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<SalesProductEntity> salesProductEntities = new ArrayList<>();

}

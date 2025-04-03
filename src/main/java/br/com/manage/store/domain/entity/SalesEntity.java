package br.com.manage.store.domain.entity;

import br.com.manage.store.domain.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_customer_id", referencedColumnName = "id")
    private CustomerEntity customerEntity;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "discount_Percentage")
    private BigDecimal discountPercentage;

    @OneToMany(mappedBy = "salesEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SalesProductEntity> productEntities = new ArrayList<>();

    @Column(name = "total_Items")
    private int totalItems;

    @Column(name = "price_With_Discount")
    private BigDecimal priceWithDiscount;

    @Column(name = "total_Price")
    private BigDecimal totalPrice;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createAt = LocalDateTime.now();

}

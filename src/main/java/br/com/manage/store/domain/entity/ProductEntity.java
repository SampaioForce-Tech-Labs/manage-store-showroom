package br.com.manage.store.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "amount")
    private int amount;

    @Column(name = "size_item")
    private String size;

    @Column(name = "stock")
    private int stock;

    @Column(name = "description")
    private String description;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private String category;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "subcategory")
    private String subCategory;

    @CreationTimestamp
    @Column(name = "create_at", updatable = false)
    private LocalDateTime createAt;

}



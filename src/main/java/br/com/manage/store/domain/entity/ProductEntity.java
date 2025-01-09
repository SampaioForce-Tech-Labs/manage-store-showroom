package br.com.manage.store.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String code;

    @Column(name = "name_product", nullable = false, length = 60)
    private String nameProduct;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private int amount;

    @Column(name = "size_item", length = 60)
    private String size;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true, length = 50)
    private String category;

    @Column(nullable = true)
    private LocalDateTime createAt;

}

package br.com.manage.store.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_rate")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "rate_name")
    private String rateName;
    @Column(name = "rate_amount")
    private double rateAmount;
    @Column(name = "number_installments")
    private Integer numberInstallments;
}

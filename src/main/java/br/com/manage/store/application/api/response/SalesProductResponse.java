package br.com.manage.store.application.api.response;

import br.com.manage.store.domain.entity.SalesEntity;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesProductResponse {

    private Long id;
    private String code;
    private String name;
    private BigDecimal priceUnit;
    private BigDecimal priceTotal;
    private int quantityInStock;
}

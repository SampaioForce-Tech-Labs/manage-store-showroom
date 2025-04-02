package br.com.manage.store.application.api.request;

import br.com.manage.store.domain.entity.SalesEntity;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesProductRequest {

    private String code;
    private String name;
    private BigDecimal priceUnit;
    private BigDecimal priceTotal;
    private int quantityInStock;
}

package br.com.manage.store.application.api.request;

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

    @Size(message = "{product.size.code}", max = 50)
    private String code;

    @DecimalMin(value = "1", message = "{sales.decimalmin.amount}")
    private int amount;
}

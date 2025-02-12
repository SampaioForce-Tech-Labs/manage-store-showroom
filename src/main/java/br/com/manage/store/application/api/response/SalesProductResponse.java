package br.com.manage.store.application.api.response;

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
    private int amount;
    private Double discount;
    private BigDecimal price;
    private BigDecimal priceWithDiscount;
}

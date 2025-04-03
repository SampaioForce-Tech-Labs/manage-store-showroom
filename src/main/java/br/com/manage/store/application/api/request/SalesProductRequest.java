package br.com.manage.store.application.api.request;

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

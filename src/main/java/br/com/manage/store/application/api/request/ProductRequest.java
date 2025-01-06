package br.com.manage.store.application.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String code;
    private String nameProduct;
    private BigDecimal price;
    private Integer amount;
    private String size;
    private Integer stock;
    private String description;
    private String category;
}

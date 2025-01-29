package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "{error.notblank.name}")
    private String name;
    private String code;
    @NotNull(message = "{error.notnull.price}")
    private BigDecimal price;
    @NotNull(message = "{error.notnull.amount}")
    private Integer amount;
    private String size;
    @NotBlank(message = "{error.notblank.category}")
    private String category;
    @NotBlank(message = "{error.notblank.subcategory}")
    private String subcategory;
}

package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @Size(message = "{product.size.code}", max = 50)
    private String code;

    @NotBlank(message = "{product.notblank.name}")
    @Size(message = "{product.size.name}", max = 60)
    private String name;

    @DecimalMin(value = "0.0", message = "{product.decimalmin.discountPercentage}")
    @DecimalMax(value = "100.0", message = "{product.decimalmax.discountPercentage}")
    private double discountPercentage;

    @NotNull(message = "{product.notnull.price}")
    @DecimalMin(value = "0.0", message = "{product.decimalmin.price}")
    private BigDecimal price;

    @NotNull(message = "{product.notnull.amount}")
    private Integer amount;

    @Size(message = "{product.size.size_item}", max = 60)
    private String size;

    private String description;

    @NotBlank(message = "{product.notblank.category}")
    @Size(message = "{product.size.category}", max = 20)
    private String category;

    @NotBlank(message = "{product.notblank.subcategory}")
    @Size(message = "{product.size.subcategory}", max = 20)
    private String subCategory;
}

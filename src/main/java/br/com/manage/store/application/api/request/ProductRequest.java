package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String code;
    @NotBlank(message = "{error.notblank.name}")
    private String name;
    @NotNull(message = "{error.notnull.price}")
    private BigDecimal price;
    @NotNull(message = "{error.notnull.amount}")
    private Integer amount;
    @Size(message = "{erro.size.size_item}", max = 20)
    private String size;
    @NotBlank(message = "{error.notblank.category}")
    private String category;
    @NotBlank(message = "{error.notblank.subcategory}")
    private String subCategory;
}

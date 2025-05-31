package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.DecimalMin;
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
public class SalesProductRequest {

    @Size(message = "{sales-product.size.code}", max = 50)
    private String code;

    @NotBlank(message = "{sales-product.notblank.name}")
    @Size(message = "{sales-product.size.name}", max = 60)
    private String name;

    @DecimalMin(value = "0.0", message = "{sales-product.decimalmin.unitPrice}")
    @NotNull(message = "{sales-product.notnull.unitPrice}")
    private BigDecimal unitPrice;

    @DecimalMin(value = "0.0", message = "{sales-product.decimalmin.totalPrice}")
    @NotNull(message = "{sales-product.notnull.totalPrice}")
    private BigDecimal totalPrice;

    private BigDecimal priceWithDiscount;

    @DecimalMin(value = "0", message = "{sales-product.decimalmin.quantityInStock}")
    @NotNull(message = "{sales-product.notnull.amount}")
    private int amount;
}

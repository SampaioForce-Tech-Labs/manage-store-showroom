package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRequest {

//    @CPF(message = "{sales.cpf.customerCpf}")
    private String customerCpf;

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.discountPercentage}")
    @DecimalMax(value = "100.0", message = "{sales.decimalmax.discountPercentage}")
//    @NotBlank(message = "{sales.notblank.discountPercentage}")
    private BigDecimal discountPercentage;

    @DecimalMin(value = "0", message = "{sales.decimalmin.totalItems}")
    @NotNull(message = "{sales.notnull.totalItems}")
    private int totalItems;

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.priceWithDiscount}")
    private BigDecimal priceWithDiscount;

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.totalPrice}")
//    @NotNull(message = "{sales.notnull.totalPrice}")
    private BigDecimal totalPrice;

    private List<SalesProductRequest> products = new ArrayList<>();
}

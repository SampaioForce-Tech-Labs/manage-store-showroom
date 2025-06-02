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
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRequest {

    @CPF(message = "{sales.cpf.customerCpf}")
    private String customerCpf;

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.discountPercentage}")
    @DecimalMax(value = "100.0", message = "{sales.decimalmax.discountPercentage}")
    private BigDecimal discountPercentage;

    @DecimalMin(value = "0", message = "{sales.decimalmin.totalItems}")
    @NotNull(message = "{sales.notnull.totalItems}")
    private int totalItems;

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.totalPrice}")
    @NotNull(message = "{sales.notnull.totalPrice}")
    private BigDecimal totalPrice;

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.subtotal}")
    @NotNull(message = "{sales.notnull.subtotal}")
    private BigDecimal subtotal;

    @NotBlank(message = "{sales.notblank.paymentMethod}")
    private String paymentMethod;

    @DecimalMin(value = "0", message = "{sales.decimalmin.numberInstallments}")
    private int numberInstallments;

    private String rateName;
    private BigDecimal rateAmount;

    private Date endDate;
    private Date startDate;

    private List<SalesProductRequest> products = new ArrayList<>();
}

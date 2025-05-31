package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesCalcRequest {

    @DecimalMin(value = "0.0", message = "{sales.decimalmin.discount}")
    @DecimalMax(value = "100.0", message = "{sales.decimalmax.discount}")
    @NotBlank(message = "{sales.notblank.discount}")
    private Double discount;
    private List<SalesProductRequest> salesProductRequest;
}
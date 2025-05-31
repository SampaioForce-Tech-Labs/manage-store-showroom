package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateRequest {
    @NotNull(message = "Campo não pode ser nulo.")
    private String rateName;
    @NotNull(message = "Campo não pode ser nulo.")
    private double rateAmount;
    @NotNull(message = "Campo não pode ser nulo.")
    private Integer numberInstallments;
}

package br.com.manage.store.application.api.response;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RateResponse {
    private Long id;
    private String rateName;
    private double rateAmount;
    private Integer numberInstallments;
}

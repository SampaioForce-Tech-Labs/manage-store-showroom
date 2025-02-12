package br.com.manage.store.application.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesCalcResponse {
    private int totalItems;
    private Double discount;
    private BigDecimal valueTotalDiscount;
    private BigDecimal totalPayable;
}

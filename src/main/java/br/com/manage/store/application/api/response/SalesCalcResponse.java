package br.com.manage.store.application.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesCalcResponse {
    private int totalItems;
    private Double discount;
    private BigDecimal priceWithDiscount;
    private BigDecimal priceTotal;
    private BigDecimal totalDiscount;
    private List<ProductResponse> productResponses;
}

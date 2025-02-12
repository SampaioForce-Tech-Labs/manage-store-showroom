package br.com.manage.store.application.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesResponse {
    private Long id;
    private int totalItems;
    private BigDecimal discountTotalSum;
    private BigDecimal totalSum;
    private BigDecimal totalWithDiscount;
    private LocalDateTime createAt;
    private List<SalesProductResponse> salesProductEntities;
}

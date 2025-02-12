package br.com.manage.store.application.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesRequest {
    private int totalItems;
    private BigDecimal discountTotalSum;
    private BigDecimal totalSum;
    private BigDecimal totalWithDiscount;
    private String customer;
    private List<SalesProductRequest> salesProductEntities = new ArrayList<>();
}

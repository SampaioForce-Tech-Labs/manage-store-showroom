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
    private String customerCpf;
    private BigDecimal discountPercentage;
    private List<SalesProductRequest> products = new ArrayList<>();
    private int totalItems;
    private  BigDecimal priceWithDiscount;
    private BigDecimal totalPrice;
}

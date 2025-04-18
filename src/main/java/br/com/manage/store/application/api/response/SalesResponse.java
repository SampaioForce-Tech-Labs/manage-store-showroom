package br.com.manage.store.application.api.response;

import br.com.manage.store.domain.enums.StatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesResponse {
    private Long id;
    private String customerCpf;
    private StatusEnum status;
    private BigDecimal discountPercentage;
    private int totalItems;
    private BigDecimal totalPrice;
    private  BigDecimal subtotal;
    private String paymentMethod;
    private int numberInstallments;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime createAt;
    private List<SalesProductResponse> products = new ArrayList<>();
}

package br.com.manage.store.application.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String code;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal priceWithDiscount;
    private double discountPercentage;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private BigDecimal price;
    private Integer amount;
    private String size;
    private String description;
    private String category;
    private String subCategory;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createAt;
}

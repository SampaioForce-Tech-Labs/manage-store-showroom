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
    private String name;
    private BigDecimal price;
    private Integer amount;
    private String size;
    private Integer stock;
    private String description;
    private String category;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createAt;
}

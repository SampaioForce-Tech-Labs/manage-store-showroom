package br.com.manage.store.application.api.filter;


import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.domain.entity.SalesEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationField;
import br.com.manage.store.infrastructure.component.specification.SpecificationOperation;
import lombok.Data;

import java.math.BigDecimal;

@Data
@SpecificationEntity(value = SalesEntity.class)
public class SalesFilterTO {
//    @SpecificationField(property = "customerCpf", operation = SpecificationOperation.LIKE_IGNORE_CASE)
//    private CustomerEntity customerCpf;
    @SpecificationField(property = "status", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String status;
    @SpecificationField(property = "totalPrice")
    private BigDecimal totalPrice;
//    @SpecificationField(property = "category", operation = SpecificationOperation.LIKE_IGNORE_CASE)
//    private String category;
}

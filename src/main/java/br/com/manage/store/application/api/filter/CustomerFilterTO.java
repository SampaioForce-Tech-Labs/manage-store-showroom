package br.com.manage.store.application.api.filter;

import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationField;
import br.com.manage.store.infrastructure.component.specification.SpecificationOperation;
import lombok.Data;

import java.util.Date;

@SpecificationEntity(value = CustomerEntity.class)
@Data
public class CustomerFilterTO {

    @SpecificationField(property = "name", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String name;
    @SpecificationField(property = "cpf", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String cpf;
    @SpecificationField(property = "phone", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String phone;
}

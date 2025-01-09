package br.com.manage.store.application.api.filter;


import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationField;
import br.com.manage.store.infrastructure.component.specification.SpecificationOperation;
import lombok.Data;

@Data
@SpecificationEntity(value = ProductEntity.class)
public class ProductFilterTO {
    @SpecificationField(property = "name", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String name;
    @SpecificationField(property = "description", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String description;
    @SpecificationField(property = "category", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String category;
}

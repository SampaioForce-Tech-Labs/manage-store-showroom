package br.com.manage.store.application.api.filter;


import br.com.manage.store.domain.entity.SalesEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationEntity;
import lombok.Data;

@Data
@SpecificationEntity(value = SalesEntity.class)
public class SalesFilterTO {
//    @SpecificationField(property = "code", operation = SpecificationOperation.LIKE_IGNORE_CASE)
//    private String code;
//    @SpecificationField(property = "name", operation = SpecificationOperation.LIKE_IGNORE_CASE)
//    private String name;
//    @SpecificationField(property = "description", operation = SpecificationOperation.LIKE_IGNORE_CASE)
//    private String description;
//    @SpecificationField(property = "category", operation = SpecificationOperation.LIKE_IGNORE_CASE)
//    private String category;
}

package br.com.manage.store.application.api.filter;

import br.com.manage.store.domain.entity.ClientEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationEntity;
import br.com.manage.store.infrastructure.annotation.SpecificationField;
import br.com.manage.store.infrastructure.component.specification.SpecificationOperation;
import lombok.Data;

import java.util.Date;

@SpecificationEntity(value = ClientEntity.class)
@Data
public class ClientFilterTO {

    @SpecificationField(property = "name", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String name;
    @SpecificationField(property = "cpf", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String cpf;
    @SpecificationField(property = "email", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String email;
    @SpecificationField(property = "phone", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String phone;
    @SpecificationField(property = "dateBirth", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private Date dateBirth;
    @SpecificationField(property = "zipCode", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String zipCode;
    @SpecificationField(property = "address", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String address;
    @SpecificationField(property = "numberAddress", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String numberAddress;
    @SpecificationField(property = "complement", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String complement;
    @SpecificationField(property = "neighborhood", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String neighborhood;
    @SpecificationField(property = "city", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String city;
    @SpecificationField(property = "state", operation = SpecificationOperation.LIKE_IGNORE_CASE)
    private String state;

}

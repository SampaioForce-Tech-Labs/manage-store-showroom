package br.com.manage.store.application.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "{error.notblank.name}")
    private String name;
    @CPF(message = "{error.cpf.cpf}")
    private String cpf;
    @Email(message = "{error.email.email}")
    private String email;
    private String phone;
    private Date dateBirth;
    @Valid
    private AddressRequest addressData;
    @Valid
    private List<ReferencePersonRequest> referenceEntityList = new ArrayList<>();
}

package br.com.manage.store.application.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "{customer.notblank.name}")
    @Size(message = "{customer.size.name}", max = 60)
    private String name;

    //    @CPF(message = "{customer.cpf.cpf}")
    @Size(message = "{customer.size.cpf}", max = 14)
    private String cpf;

    //    @Email(message = "{customer.email.email}")
    @Size(message = "{customer.size.email}", max = 60)
    private String email;

    @Size(message = "{customer.size.phone}", max = 19)
    private String phone;

    private Date dateBirth;

    @Valid
    private AddressRequest addressData;

    @Size(message = "{customer.size.maritalStatus}", max = 20)
    private String maritalStatus;

    @Size(message = "{customer.size.enterprise}", max = 50)
    private String enterprise;

    @Size(message = "{customer.size.businessPhone}", max = 19)
    private String businessPhone;

    @Size(message = "{customer.size.lengthService}", max = 20)
    private String lengthService;

    @Size(message = "{customer.size.zipCode}", max = 9)
    private String businessZipCode;

    @Size(message = "{customer.size.businessAddress}", max = 60)
    private String businessAddress;

    @Size(message = "{customer.size.businessCity}", max = 20)
    private String businessCity;

    @Size(message = "{customer.size.businessState}", max = 20)
    private String businessState;

    @Size(message = "{customer.size.businessPosition}", max = 50)
    private String businessPosition;

    @Size(message = "{customer.size.bank}", max = 20)
    private String bank;

    @Size(message = "{customer.size.agency}", max = 15)
    private String agency;

    @Size(message = "{customer.size.father}", max = 60)
    private String father;

    @Size(message = "{customer.size.mother}", max = 60)
    private String mother;

    @Valid
    private List<ReferencePersonRequest> referenceEntityList = new ArrayList<>();
}

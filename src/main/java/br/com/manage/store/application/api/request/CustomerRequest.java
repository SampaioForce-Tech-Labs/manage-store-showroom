package br.com.manage.store.application.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {

    private String name;
    private String cpf;
    private String email;
    private String phone;
    private Date dateBirth;
    private String zipCode;
    private String address;
    private String numberAddress;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
}

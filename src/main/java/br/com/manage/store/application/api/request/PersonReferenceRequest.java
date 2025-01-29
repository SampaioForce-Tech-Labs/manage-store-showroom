package br.com.manage.store.application.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonReferenceRequest {
    private String name;
    private String phone;
    private String zipCode;
    private String address;
    private String numberAddress;
    private String neighborhood;
    private String complement;
    private String city;
    private String state;
    private String observation;
}

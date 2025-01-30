package br.com.manage.store.application.api.request;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferencePersonRequest {
    private String name;
    private String phone;
    @Valid
    private AddressRequest addressData;
    private String observation;
}

package br.com.manage.store.application.api.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferencePersonRequest {

    private Long id;

    @Size(message = "{reference.size.name}", max = 60)
    private String name;

    @Size(message = "reference.size.phone", max = 15)
    private String phone;

    @Valid
    private AddressRequest addressData;
}

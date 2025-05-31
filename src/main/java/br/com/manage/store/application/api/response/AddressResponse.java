package br.com.manage.store.application.api.response;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {
    @Size(message = "{error.size.zip_code}", max = 8)
    private String zipCode;
    private String address;
    private String number;
    private String complement;
    private String referencePoint;
    private String city;
    @Size(message = "{error.size.state}", max = 2)
    private String state;
}

package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {
    @Size(message = "{error.size.zip_code}", max = 8)
    private String zipCode;
    private String address;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    @Size(message = "{error.size.state}", max = 2)
    private String state;
}

package br.com.manage.store.application.api.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRequest {

    @Size(message = "{address.size.zipCode}", max = 9)
    private String zipCode;

    @Size(message = "{address.size.address}", max = 60)
    private String address;

    @Size(message = "{address.size.number}", max = 10)
    private String number;

    @Size(message = "{address.size.complement}", max = 20)
    private String complement;

    @Size(message = "{address.size.referencePoint}", max = 60)
    private String referencePoint;

    @Size(message = "{address.size.city}", max = 20)
    private String city;

    @Size(message = "{error.size.state}", max = 20)
    private String state;
}

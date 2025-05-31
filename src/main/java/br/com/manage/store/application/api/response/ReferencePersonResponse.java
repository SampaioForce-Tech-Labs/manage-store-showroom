package br.com.manage.store.application.api.response;

import br.com.manage.store.application.api.request.AddressRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReferencePersonResponse {
    private Long id;
    private String name;
    private String phone;
    private AddressRequest addressData;
}

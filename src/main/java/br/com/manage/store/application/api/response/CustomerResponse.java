package br.com.manage.store.application.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private Date dateBirth;
    private AddressResponse addressData;
    private String maritalStatus;
    private String enterprise;
    private String businessPhone;
    private String lengthService;
    private String businessZipCode;
    private String businessAddress;
    private String businessCity;
    private String businessState;
    private String businessPosition;
    private String bank;
    private String agency;
    private String father;
    private String mother;
    private List<ReferencePersonResponse> referenceEntityList;
    private LocalDateTime createAt;
}

package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.AddressRequest;
import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.application.api.request.ReferencePersonRequest;
import br.com.manage.store.application.api.response.CustomerResponse;
import br.com.manage.store.domain.entity.AddressData;
import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.domain.entity.ReferencePersonEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private GenericMapper mapper;

    @InjectMocks
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<CustomerRequest> requestArgumentCaptor;

    @Captor
    private ArgumentCaptor<CustomerEntity> entityArgumentCaptor;

    @Captor
    private ArgumentCaptor<CustomerResponse> responseArgumentCaptor;

    private CustomerRequest customerRequest;
    private CustomerResponse customerResponse;
    private CustomerEntity customerEntity;


    @BeforeEach
    void setUp() {
        Date dateBirth = java.sql.Date.valueOf(LocalDate.of(1995, 8, 15));

        customerRequest = new CustomerRequest("João da Silva", "123.456.789-00", "joao.silva@example.com", "MG-12.345.678", dateBirth, new AddressRequest(), "Masculino", "Solteiro", "Brasileiro", "Engenheiro de Software", "Empresa XYZ", "31999998888", "31988887777", "joaosilva", "senha123", "joaosilva123", "123456789", "1234", "Visa", List.of(new ReferencePersonRequest()));

        customerEntity = new CustomerEntity(1L, // id
                "João da Silva", // name
                "123.456.789-00", // cpf
                "joao.silva@example.com", // email
                "31999998888", // phone
                java.sql.Date.valueOf(LocalDate.of(1995, 8, 15)), // dateBirth
                "Solteiro", // maritalStatus
                "Empresa XYZ", // enterprise
                "31988887777", // businessPhone
                "2 anos", // lengthService
                "30100-000", // businessZipCode
                "Av. Paulista, 1000", // businessAddress
                "São Paulo", // businessCity
                "SP", // businessState
                "Engenheiro de Software", // businessPosition
                "Visa", // bank
                "1234", // agency
                "joaosilva123", // father
                "senha123", // mother
                new AddressData(), LocalDateTime.now(), // createAt (pode ser null, já que o Hibernate gera automaticamente)
                new ArrayList<>(), // salesEntities
                List.of( // referenceEntityList
                        new ReferencePersonEntity()));

    }

    @Nested
    class CreateCustomer {
        @Test
        @DisplayName(value = "Should create a customer with success.")
        void ShouldCreateACustomerWithSuccess() {
            // Arrange
            customerResponse = new CustomerResponse();
            customerResponse.setId(1L);
            doReturn(customerEntity).when(mapper).map(customerRequest, CustomerEntity.class);
            doReturn(customerEntity).when(customerRepository).save(entityArgumentCaptor.capture());
            doReturn(customerResponse).when(mapper).map(customerEntity, CustomerResponse.class);
            //Act
            var response = customerService.create(customerRequest);
            CustomerEntity capturedEntity = entityArgumentCaptor.getValue();
            //Assert
            verify(customerRepository, times(1)).save(any());
            Assertions.assertEquals(capturedEntity.getId(), response.getId());
        }
    }
}
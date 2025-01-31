package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.ICustomerController;
import br.com.manage.store.application.api.filter.CustomerFilterTO;
import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.application.api.response.CustomerResponse;
import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.domain.service.ICustomerService;
import br.com.manage.store.infrastructure.component.specification.SpecificationFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController implements ICustomerController {

    private final ICustomerService clientService;
    private final SpecificationFactory<CustomerEntity> specificationFactory;

    @Override
    public ResponseEntity<CustomerResponse> create(CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(request));
    }

    @Override
    public ResponseEntity<CustomerResponse> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CustomerResponse> update(Long id, CustomerRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(id, request));
    }

    @Override
    public ResponseEntity<Page<CustomerResponse>> findAll(CustomerFilterTO customerFilterTo, int size, int page) {
        Specification<CustomerEntity> specification = specificationFactory.create(customerFilterTo);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(specification, size, page));
    }
}

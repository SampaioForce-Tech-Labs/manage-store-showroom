package br.com.manage.store.domain.service;

import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.application.api.response.CustomerResponse;
import br.com.manage.store.domain.entity.CustomerEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ICustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse findById(Long id);

    void delete(Long id);

    CustomerResponse update(Long id, CustomerRequest request);

    List<CustomerResponse> findAll(Specification<CustomerEntity> specification, int size, int page);
}

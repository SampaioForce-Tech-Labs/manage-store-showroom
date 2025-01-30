package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.application.api.response.CustomerResponse;
import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.domain.entity.ReferencePersonEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.ICustomerService;
import br.com.manage.store.infrastructure.component.VerifyExists;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.manage.store.infrastructure.util.VerifyNotNullUtils.notNull;

@Service
@AllArgsConstructor
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;
    private final VerifyExists verifyExists;
    private final GenericMapper mapper;

    @Transactional
    @Override
    public CustomerResponse create(CustomerRequest request) {
        notNull(request);
        verifyExists.verifyConflictEmailOrCpf(request.getEmail(), request.getCpf());
        var customerEntity = mapper.map(request, CustomerEntity.class);
        var personList = mapper.mapAll(request.getReferenceEntityList(), ReferencePersonEntity.class);
        personList.forEach(ref ->
                ref.setCustomerEntity(customerEntity));
        customerEntity.setReferenceEntityList(personList);
        return mapper.map(customerRepository.save(customerEntity), CustomerResponse.class);
    }

    @Override
    public CustomerResponse findById(Long id) {
        notNull(id);
        var customer = verifyExists.getEntityExists(id);
        return mapper.map(customer, CustomerResponse.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        notNull(id);
        verifyExists.verifyExists(id);
        customerRepository.deleteById(id);
    }

    @Transactional
    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {
        notNull(id, request);
        var customer = verifyExists.getEntityExists(id);
        verifyExists.verifyConflict(customer, request);
        BeanUtils.copyProperties(request, customer, "id");
        return mapper.map(customerRepository.save(customer), CustomerResponse.class);
    }

    @Override
    public List<CustomerResponse> findAll(Specification<CustomerEntity> specification, int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<CustomerEntity> custormers = customerRepository.findAll(specification, pageable);
        return mapper.mapAll(custormers.stream().toList(), CustomerResponse.class);
    }
}

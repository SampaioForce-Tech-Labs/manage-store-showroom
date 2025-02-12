package br.com.manage.store.infrastructure.component;

import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@AllArgsConstructor
public class CustomerExists {

    private final CustomerRepository customerRepository;

    public void verifyExistsCustomer(Object source) {
        if (source instanceof Long && !customerRepository.existsById((Long) source)) {
            throw new NotFoundException("ID: " + source.toString());
        } else if (source instanceof String && customerRepository.existsByCpf((String) source)) {
            throw new ConflictException("CPF: " + source.toString());
        }
    }

    public void verifyConflictCustomer(CustomerEntity entity, CustomerRequest request) {
        if (StringUtils.hasText(request.getCpf())) {
            if (!entity.getCpf().equals(request.getCpf()) && customerRepository.existsByCpf(request.getCpf())) {
                throw new ConflictException("CPF: " + request.getCpf());
            }
        }
        if (StringUtils.hasText(request.getEmail())) {
            if (!entity.getEmail().equals(request.getEmail()) && customerRepository.existsByEmail(request.getEmail())) {
                throw new ConflictException("EMAIL: " + request.getEmail());
            }
        }
    }

    public void verifyConflictEmailOrCpf(String email, String cpf) {
        if (email != null && customerRepository.existsByEmail(email)) {
            throw new ConflictException("EMAIL: " + email);
        }
        if (cpf != null && customerRepository.existsByCpf(cpf)) {
            throw new ConflictException("CPF: " + cpf);
        }
    }

    public CustomerEntity getEntityExistsIdCustomer(Long source) {
        return customerRepository.findById(source).orElseThrow(() -> new NotFoundException("ID: " + source.toString()));
    }
}

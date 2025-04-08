package br.com.manage.store.infrastructure.util;

import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.domain.entity.CustomerEntity;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

@AllArgsConstructor
public class CustomerExistsUtil {

    public static void verifyExistsCustomer(CustomerRepository customerRepository, Object source) {
        if (source instanceof Long && !customerRepository.existsById((Long) source)) {
            throw new NotFoundException(source.toString());
        } else if (source instanceof String && customerRepository.existsByCpf((String) source)) {
            throw new ConflictException(source.toString());
        }
    }

    public static void verifyConflictCustomer(CustomerRepository customerRepository, CustomerEntity entity, CustomerRequest request) {
        if (StringUtils.hasText(request.getCpf())) {
            if (!entity.getCpf().equals(request.getCpf()) && customerRepository.existsByCpf(request.getCpf())) {
                throw new ConflictException(request.getCpf());
            }
        }
        if (StringUtils.hasText(request.getEmail())) {
            if (!entity.getEmail().equals(request.getEmail()) && customerRepository.existsByEmail(request.getEmail())) {
                throw new ConflictException(request.getEmail());
            }
        }
    }

    public static void verifyConflictEmailOrCpf(CustomerRepository customerRepository, String email, String cpf) {
        if (email != null && customerRepository.existsByEmail(email)) {
            throw new ConflictException(email);
        }
        if (cpf != null && customerRepository.existsByCpf(cpf)) {
            throw new ConflictException(cpf);
        }
    }

    public static CustomerEntity getEntityExistsIdCustomer(CustomerRepository customerRepository, Long source) {
        return customerRepository.findById(source).orElseThrow(() -> new NotFoundException(source.toString()));
    }
}

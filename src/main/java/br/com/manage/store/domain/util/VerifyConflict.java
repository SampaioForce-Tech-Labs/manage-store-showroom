package br.com.manage.store.domain.util;

import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VerifyConflict {

    private final ProductRepository productRepository;

    public void conflictCode(String value){
        if (productRepository.existsByCode(value)){
            throw new ConflictException("CODE: " + value);
        }
    }
}

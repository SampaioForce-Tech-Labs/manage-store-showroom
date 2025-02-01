package br.com.manage.store.infrastructure.component;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductExists {

    private final ProductRepository productRepository;

    public void verifyConflictProduct(String code) {
        if (code != null && productRepository.existsByCode(code)) {
            throw new ConflictException("Código: " + code);
        }
    }

    public void verifyExistsIdProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Id: " + id);
        }
    }

    public ProductEntity getProductExists(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new NotFoundException("ID: " + id));
    }

    public void verifyConflictEntityAndRequestCode(ProductEntity product, ProductRequest request) {
        if (!product.getCode().equals(request.getCode())){
            if (productRepository.existsByCode(request.getCode())){
                throw new ConflictException("Código: " + request.getCode());
            }
        }
    }
}

package br.com.manage.store.infrastructure.component;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.request.SalesProductRequest;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ProductExists {

    private final ProductRepository productRepository;

    public void verifyConflictProduct(String code) {
        if (code != null && productRepository.existsByCode(code)) {
            throw new ConflictException(code);
        }
    }

    public void verifyExistsIdProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(id.toString());
        }
    }

    public ProductEntity getProductExists(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }

    public ProductEntity getProductExistsCode(String code) {
        return productRepository.findByCode(code).orElseThrow(() -> new NotFoundException(code));
    }

    public void verifyConflictEntityAndRequestCode(ProductEntity product, ProductRequest request) {
        if (!product.getCode().equals(request.getCode())) {
            if (productRepository.existsByCode(request.getCode())) {
                throw new ConflictException(request.getCode());
            }
        }
    }

    public Map<String, ProductEntity> verifyProductAndMap(List<SalesProductRequest> request) {
        return request.stream().map(ref -> {
            var entity = productRepository.findByCode(ref.getCode()).orElseThrow(() -> new NotFoundException(ref.getCode()));
            if (ref.getAmount() > entity.getQuantityInStock()) {
                throw new NullPointerException("Quantidade insuficiente!");
            }
            return entity;
        }).collect(Collectors.toMap(ProductEntity::getCode, entity -> entity));
    }
}

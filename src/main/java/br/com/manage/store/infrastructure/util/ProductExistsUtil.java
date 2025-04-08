package br.com.manage.store.infrastructure.util;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.request.SalesProductRequest;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ProductExistsUtil {


    public static void verifyConflictProduct(ProductRepository productRepository, String code) {
        if (code != null && productRepository.existsByCode(code)) {
            throw new ConflictException(code);
        }
    }

    public static void verifyExistsIdProduct(ProductRepository productRepository, Long id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException(id.toString());
        }
    }

    public static ProductEntity getProductExists(ProductRepository productRepository, Long id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(id.toString()));
    }

    public static ProductEntity getProductExistsCode(ProductRepository productRepository, String code) {
        return productRepository.findByCode(code).orElseThrow(() -> new NotFoundException(code));
    }

    public static void verifyConflictEntityAndRequestCode(ProductRepository productRepository, ProductEntity product, ProductRequest request) {
        if (!product.getCode().equals(request.getCode())) {
            if (productRepository.existsByCode(request.getCode())) {
                throw new ConflictException(request.getCode());
            }
        }
    }

    public static Map<String, ProductEntity> verifyProductAndMap(ProductRepository productRepository, List<SalesProductRequest> request) {
        return request.stream().map(ref -> {
            var entity = productRepository.findByCode(ref.getCode()).orElseThrow(() -> new NotFoundException(ref.getCode()));
            if (ref.getAmount() > entity.getQuantityInStock()) {
                throw new NullPointerException("Quantidade insuficiente!");
            }
            return entity;
        }).collect(Collectors.toMap(ProductEntity::getCode, entity -> entity));
    }
}

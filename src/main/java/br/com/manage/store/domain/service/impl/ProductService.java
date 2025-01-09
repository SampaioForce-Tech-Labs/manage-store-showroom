package br.com.manage.store.domain.service.impl;

import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.service.IProductService;
import br.com.manage.store.domain.util.CheckNotNull;
import br.com.manage.store.domain.util.ComparePrice;
import br.com.manage.store.domain.util.VerifyConflict;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final CheckNotNull checkNotNull;
    private final VerifyConflict verifyConflict;
    private final ProductRepository productRepository;
    private final ComparePrice comparePrice;

    @Transactional
    @Override
    public ProductEntity createProduct(ProductEntity entity) {

        checkNotNull.validation(entity);
        verifyConflict.conflictCode(entity.getCode());
        comparePrice.checkPrice(entity.getPrice());

        entity.setCreateAt(LocalDateTime.now());

        return productRepository.save(entity);
    }

    @Override
    public ProductEntity findByIdProduct(Long id) {

        checkNotNull.validation(id);

        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {

        checkNotNull.validation(id);

        if (!productRepository.existsById(id)) {
            throw new NotFoundException("ID: " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity entity) {

        checkNotNull.validation(id, entity);
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
        entity.setId(id);

        return productRepository.save(entity);
    }
}

package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.service.IProductService;
import br.com.manage.store.domain.util.ComparePrice;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.manage.store.domain.util.CheckNotNull.validation;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public ProductEntity createProduct(ProductEntity entity) {

        validation(entity);
        ComparePrice.checkPrice(entity.getPrice());
        entity.setCreateAt(LocalDateTime.now());

        return productRepository.save(entity);
    }

    @Override
    public ProductEntity findByIdProduct(Long id) {

        validation(id);
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
    }

    @Transactional
    @Override
    public void deleteProduct(Long id) {

        validation(id);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("ID: " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity entity) {

        validation(id, entity);
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
        BeanUtils.copyProperties(entity, product, "id");
        return productRepository.save(product);
    }

    @Override
    public List<ProductResponse> findAllProduct(Specification<ProductEntity> specification, int size, int page) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<ProductEntity> productEntities = productRepository.findAll(specification, pageable);
        return productEntities.stream().map(p -> modelMapper.map(p, ProductResponse.class)).collect(Collectors.toList());
    }
}

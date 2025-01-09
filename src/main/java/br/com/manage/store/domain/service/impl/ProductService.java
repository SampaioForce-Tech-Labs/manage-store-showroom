package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.IProductService;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
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

import static br.com.manage.store.infrastructure.util.ComparePrice.checkPrice;
import static br.com.manage.store.infrastructure.util.VerifyNotNull.notNull;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final GenericMapper mapper;

    @Transactional
    @Override
    public ProductResponse create(ProductRequest request) {
        notNull(request);
        checkPrice(request.getPrice());
        var entity = mapper.map(request, ProductEntity.class);
        return mapper.map(productRepository.save(entity), ProductResponse.class);
    }

    @Override
    public ProductResponse findById(Long id) {
        notNull(id);
        var entity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
        return mapper.map(entity, ProductResponse.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        notNull(id);
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("ID: " + id);
        }

        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        notNull(List.of(id, request));
        var product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
        BeanUtils.copyProperties(request, product, "id");
        return mapper.map(productRepository.save(product), ProductResponse.class);
    }

    @Override
    public List<ProductResponse> findAll(Specification<ProductEntity> specification, int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<ProductEntity> products = productRepository.findAll(specification, pageable);
        return mapper.mapAll(products.stream().toList(), ProductResponse.class);
    }
}

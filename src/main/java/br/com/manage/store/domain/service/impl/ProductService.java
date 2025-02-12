package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.OptionalCategory;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.enums.ISubcategory;
import br.com.manage.store.domain.enums.category.CategoryEnum;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.IProductService;
import br.com.manage.store.infrastructure.component.ProductExists;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.manage.store.infrastructure.util.CalcDiscountUtil.discount;
import static br.com.manage.store.infrastructure.util.ComparePriceUtils.checkPrice;
import static br.com.manage.store.infrastructure.util.EnumCheckerUtils.isValidEnum;
import static br.com.manage.store.infrastructure.util.VerifyNotNullUtils.notNull;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final GenericMapper mapper;
    private final ProductExists productExists;

    @Transactional
    @Override
    public ProductResponse create(ProductRequest request) {
        notNull(request);
        productExists.verifyConflictProduct(request.getCode());
        checkPrice(request.getPrice());
        isValidEnum(request);
        var entity = mapper.map(request, ProductEntity.class);
        entity.setPriceWithDiscount(discount(request.getDiscountPercentage(), request.getPrice()));
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
        productExists.verifyExistsIdProduct(id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        notNull(id, request);
        isValidEnum(request);
        var product = productExists.getProductExists(id);
        productExists.verifyConflictEntityAndRequestCode(product, request);
        BeanUtils.copyProperties(request, product, "id");
        product.setPriceWithDiscount(discount(request.getDiscountPercentage(), request.getPrice()));
        return mapper.map(productRepository.save(product), ProductResponse.class);
    }

    @Override
    public Page<ProductResponse> findAll(Specification<ProductEntity> specification, int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<ProductEntity> products = productRepository.findAll(specification, pageable);
        return new PageImpl<>(mapper.mapAll(products.stream().toList(), ProductResponse.class), pageable, products.getTotalElements());
    }

    @Override
    public List<OptionalCategory> findAllCategory() {
        return Arrays.stream(CategoryEnum.values()).map(category -> {
            List<String> subcategory = new ArrayList<>();
            for (ISubcategory sub : category.getSubcategory()) {
                subcategory.add(sub.getSubcategory());
            }
            return new OptionalCategory(category.getCategory(), subcategory);
        }).collect(Collectors.toList());
    }
}

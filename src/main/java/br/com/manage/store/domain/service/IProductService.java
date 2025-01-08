package br.com.manage.store.domain.service;

import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IProductService {

    ProductEntity createProduct(ProductEntity entity);

    ProductEntity findByIdProduct(Long id);

    void deleteProduct(Long id);

    ProductEntity updateProduct(Long id, ProductEntity entity);

    List<ProductResponse> findAllProduct(Specification<ProductEntity> specification, int size, int page);
}

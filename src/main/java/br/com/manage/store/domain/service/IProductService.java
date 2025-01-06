package br.com.manage.store.domain.service;

import br.com.manage.store.domain.entity.ProductEntity;

public interface IProductService {

    ProductEntity createProduct(ProductEntity entity);

    ProductEntity findByIdProduct(Long id);

    void deleteProduct(Long id);

    ProductEntity updateProduct(Long id, ProductEntity entity);
}

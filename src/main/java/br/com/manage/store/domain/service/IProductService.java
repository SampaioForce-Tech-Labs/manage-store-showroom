package br.com.manage.store.domain.service;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.OptionalCategory;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse findById(Long id);

    void delete(Long id);

    ProductResponse update(Long id, ProductRequest request);

    Page<ProductResponse> findAll(Specification<ProductEntity> specification, int size, int page);

    List<OptionalCategory> findAllCategory();
}

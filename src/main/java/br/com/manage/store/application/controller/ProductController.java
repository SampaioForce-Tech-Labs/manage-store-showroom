package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.IProductController;
import br.com.manage.store.application.api.filter.ProductFilterTO;
import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.OptionalCategory;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.service.IProductService;
import br.com.manage.store.infrastructure.component.specification.SpecificationFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;
    private final SpecificationFactory<ProductEntity> specificationFactory;

    @Override
    public ResponseEntity<ProductResponse> create(ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.create(request));
    }

    @Override
    public ResponseEntity<ProductResponse> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> update(Long id, ProductRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.update(id, request));
    }

    @Override
    public ResponseEntity<List<ProductResponse>> findAll(ProductFilterTO filterTO, int size, int page) {
        Specification<ProductEntity> specification = specificationFactory.create(filterTO);
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(specification, size, page));
    }

    @Override
    public ResponseEntity<List<OptionalCategory>> category(){
        return ResponseEntity.ok().body(productService.findAllCategory());
    }
}

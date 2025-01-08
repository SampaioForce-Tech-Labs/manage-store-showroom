package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.IProductController;
import br.com.manage.store.application.api.filter.ProductFilterTO;
import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.service.IProductService;
import br.com.manage.store.infrastructure.component.specification.SpecificationFactory;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;
    private final ModelMapper modelMapper;
    private final SpecificationFactory<ProductEntity> specificationFactory;

    @Override
    public ResponseEntity<ProductResponse> create(ProductRequest request) {

        var response = productService.create(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<ProductResponse> findById(Long id) {

        var response = modelMapper.map(productService.findById(id), ProductResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {

        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> update(Long id, ProductRequest request) {

        var response = productService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<List<ProductResponse>> findAll(ProductFilterTO filterTO, int size, int page) {

        Specification<ProductEntity> specificaiton = specificationFactory.create(filterTO);
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll(specificaiton, size, page));
    }
}

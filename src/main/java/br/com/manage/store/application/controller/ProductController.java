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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;
    private final ModelMapper modelMapper;
    private final SpecificationFactory<ProductEntity> specificationFactory;

    @Override
    public ResponseEntity<URI> create(ProductRequest request) {

        var product = productService.createProduct(modelMapper.map(request, ProductEntity.class));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ProductResponse> findById(Long id) {

        var response = modelMapper.map(productService.findByIdProduct(id), ProductResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> update(Long id, ProductRequest request) {

        var response = productService.updateProduct(id, modelMapper.map(request, ProductEntity.class));
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(request, ProductResponse.class));
    }

    @Override
    public ResponseEntity<List<ProductResponse>> findAll(ProductFilterTO filterTO, int size, int page) {

        Specification<ProductEntity> specificaiton = specificationFactory.create(filterTO);
        return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProduct(specificaiton, size, page));
    }
}

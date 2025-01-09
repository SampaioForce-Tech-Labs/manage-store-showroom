package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.IProductController;
import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.domain.entity.ProductEntity;
import br.com.manage.store.domain.service.IProductService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
public class ProductController implements IProductController {

    private final IProductService productService;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<URI> createProduct(ProductRequest request) {

        var product = productService.createProduct(modelMapper.map(request, ProductEntity.class));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<ProductResponse> findByIdProduct(Long id) {

        var response = modelMapper.map(productService.findByIdProduct(id), ProductResponse.class);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Override
    public ResponseEntity<Void> deleteProduct(Long id) {

        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ProductResponse> updateProduct(Long id, ProductRequest request) {

        var response = productService.updateProduct(id, modelMapper.map(request, ProductEntity.class));
        return ResponseEntity.status(HttpStatus.OK).body(modelMapper.map(request, ProductResponse.class));
    }
}

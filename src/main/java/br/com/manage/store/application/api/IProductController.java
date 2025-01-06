package br.com.manage.store.application.api;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping(value = "/product")
public interface IProductController {

    @PostMapping(value = "/create", produces = "application/json")
    ResponseEntity<URI> createProduct(@RequestBody ProductRequest request);

    @GetMapping(value = "/get/{id}", produces = "application/json")
    ResponseEntity<ProductResponse> findByIdProduct(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    ResponseEntity<Void> deleteProduct(@PathVariable Long id);

    @PutMapping(value = "/update/{id}", produces = "application/json")
    ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest request);

}

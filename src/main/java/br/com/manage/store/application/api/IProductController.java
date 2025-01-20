package br.com.manage.store.application.api;

import br.com.manage.store.application.api.filter.ProductFilterTO;
import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/products")
public interface IProductController {

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest request);

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> findById(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductResponse> update(@PathVariable Long id, @RequestBody ProductRequest request);

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductResponse>> findAll(
            ProductFilterTO filterTO,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "page", defaultValue = "0") int page
    );

}

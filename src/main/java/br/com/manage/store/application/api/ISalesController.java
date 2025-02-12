package br.com.manage.store.application.api;

import br.com.manage.store.application.api.filter.ProductFilterTO;
import br.com.manage.store.application.api.request.SalesCalcRequest;
import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.SalesCalcResponse;
import br.com.manage.store.application.api.response.SalesResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/sales")
public interface ISalesController {

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SalesResponse> create(@RequestBody @Valid SalesRequest request);

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SalesResponse> findById(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SalesResponse> update(@PathVariable Long id, @RequestBody SalesRequest request);

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<SalesResponse>> findAll(
            ProductFilterTO filterTO,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "page", defaultValue = "0") int page
    );

    @GetMapping(value = "/calc-sales", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SalesCalcResponse> calcSales(@RequestBody SalesCalcRequest request);
}

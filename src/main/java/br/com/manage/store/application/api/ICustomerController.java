package br.com.manage.store.application.api;

import br.com.manage.store.application.api.filter.CustomerFilterTO;
import br.com.manage.store.application.api.request.CustomerRequest;
import br.com.manage.store.application.api.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/customers")
public interface ICustomerController {

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> create(@RequestBody @Valid CustomerRequest request);

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> findById(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<CustomerResponse> update(@PathVariable Long id, @RequestBody @Valid CustomerRequest request);

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Page<CustomerResponse>> findAll(
            CustomerFilterTO customerFilterTo,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "page", defaultValue = "0") int page
    );

}

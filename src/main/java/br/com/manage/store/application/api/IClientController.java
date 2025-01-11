package br.com.manage.store.application.api;

import br.com.manage.store.application.api.filter.ClientFilterTO;
import br.com.manage.store.application.api.request.ClientRequest;
import br.com.manage.store.application.api.response.ClientResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/clients")
public interface IClientController {

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> create(@RequestBody ClientRequest request);

    @GetMapping(value = "/find/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> findById(@PathVariable Long id);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ClientResponse> update(@PathVariable Long id, @RequestBody ClientRequest request);

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ClientResponse>> findAll(
            ClientFilterTO clientFilterTo,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "page", defaultValue = "0") int page
    );

}

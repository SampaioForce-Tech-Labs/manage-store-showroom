package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.IClientController;
import br.com.manage.store.application.api.filter.ClientFilterTO;
import br.com.manage.store.application.api.request.ClientRequest;
import br.com.manage.store.application.api.response.ClientResponse;
import br.com.manage.store.domain.entity.ClientEntity;
import br.com.manage.store.domain.service.IClientService;
import br.com.manage.store.infrastructure.component.specification.SpecificationFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController implements IClientController {

    private final IClientService clientService;
    private final SpecificationFactory<ClientEntity> specificationFactory;

    @Override
    public ResponseEntity<ClientResponse> create(ClientRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.create(request));
    }

    @Override
    public ResponseEntity<ClientResponse> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<ClientResponse> update(Long id, ClientRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.update(id, request));
    }

    @Override
    public ResponseEntity<List<ClientResponse>> findAll(ClientFilterTO clientFilterTo, int size, int page) {
        Specification<ClientEntity> specification = specificationFactory.create(clientFilterTo);
        return ResponseEntity.status(HttpStatus.OK).body(clientService.findAll(specification, size, page));
    }
}

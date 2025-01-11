package br.com.manage.store.domain.service;

import br.com.manage.store.application.api.request.ClientRequest;
import br.com.manage.store.application.api.response.ClientResponse;
import br.com.manage.store.domain.entity.ClientEntity;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IClientService {

    ClientResponse create(ClientRequest request);

    ClientResponse findById(Long id);

    void delete(Long id);

    ClientResponse update(Long id, ClientRequest request);

    List<ClientResponse> findAll(Specification<ClientEntity> specification, int size, int page);
}

package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.ClientRequest;
import br.com.manage.store.application.api.response.ClientResponse;
import br.com.manage.store.domain.entity.ClientEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.IClientService;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.com.manage.store.infrastructure.util.VerifyNotNull.notNull;

@Service
@AllArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final GenericMapper mapper;

    @Transactional
    @Override
    public ClientResponse create(ClientRequest request) {
        notNull(request);
        if (clientRepository.existsByCpf(request.getCpf())){
            throw new ConflictException("CPF: " + request.getCpf());
        }
        var entity = clientRepository.save(mapper.map(request, ClientEntity.class));
        return mapper.map(entity, ClientResponse.class);
    }

    @Override
    public ClientResponse findById(Long id) {
        notNull(id);
        var entity = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
        return mapper.map(entity, ClientResponse.class);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        notNull(id);
        if (!clientRepository.existsById(id)){
            throw new NotFoundException("ID: " + id);
        }
        clientRepository.deleteById(id);
    }

    @Transactional
    @Override
    public ClientResponse update(Long id, ClientRequest request) {
        notNull(id, request);
        var client = clientRepository.findById(id).orElseThrow(() -> new NotFoundException("ID: " + id));
        BeanUtils.copyProperties(request, client, "id");
        return mapper.map(clientRepository.save(client), ClientResponse.class);
    }

    @Override
    public List<ClientResponse> findAll(Specification<ClientEntity> specification, int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<ClientEntity> clients = clientRepository.findAll(specification, pageable);
        return mapper.mapAll(clients.stream().toList(), ClientResponse.class);
    }
}

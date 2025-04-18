package br.com.manage.store.domain.service;

import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.SalesResponse;
import br.com.manage.store.domain.entity.SalesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public interface ISalesService {

    SalesResponse create(SalesRequest request);

    SalesResponse findById(Long id);

    void delete(Long id);

    Page<SalesResponse> findAll(Specification<SalesEntity> specification, int size, int page);

}

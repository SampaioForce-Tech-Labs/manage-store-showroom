package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.ISalesController;
import br.com.manage.store.application.api.filter.SalesFilterTO;
import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.SalesResponse;
import br.com.manage.store.domain.entity.SalesEntity;
import br.com.manage.store.domain.service.ISalesService;
import br.com.manage.store.infrastructure.component.specification.SpecificationFactory;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SalesController implements ISalesController {

    private final ISalesService iSalesService;
    private final SpecificationFactory<SalesEntity> specificationFactory;

    @Override
    public ResponseEntity<SalesResponse> create(SalesRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(iSalesService.create(request));
    }

    @Override
    public ResponseEntity<SalesResponse> findById(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iSalesService.findById(id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        iSalesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Page<SalesResponse>> findAll(SalesFilterTO filterTO, int size, int page) {
        Specification<SalesEntity> specification = specificationFactory.create(filterTO);
        return ResponseEntity.status(HttpStatus.OK).body(iSalesService.findAll(specification,size,page));
    }

}

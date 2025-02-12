package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.ISalesController;
import br.com.manage.store.application.api.filter.ProductFilterTO;
import br.com.manage.store.application.api.request.SalesCalcRequest;
import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.SalesCalcResponse;
import br.com.manage.store.application.api.response.SalesResponse;
import br.com.manage.store.domain.service.ISalesService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class SalesController implements ISalesController {

    private final ISalesService iSalesService;

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
        return null;
    }

    @Override
    public ResponseEntity<SalesResponse> update(Long id, SalesRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Page<SalesResponse>> findAll(ProductFilterTO filterTO, int size, int page) {
        return null;
    }

    @Override
    public ResponseEntity<SalesCalcResponse> calcSales(SalesCalcRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(iSalesService.salesCalc(request));
    }
}

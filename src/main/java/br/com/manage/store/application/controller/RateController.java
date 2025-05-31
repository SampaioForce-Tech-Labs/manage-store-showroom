package br.com.manage.store.application.controller;

import br.com.manage.store.application.api.IRateController;
import br.com.manage.store.application.api.request.RateRequest;
import br.com.manage.store.application.api.response.RateResponse;
import br.com.manage.store.domain.service.IRateService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RateController implements IRateController {

    private final IRateService iRateService;

    @Override
    public ResponseEntity<RateResponse> createRate(RateRequest request) {
        var response = iRateService.createRate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<RateResponse> updateRate(Long id, RateRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteRate(Long id) {
        iRateService.deleteRate(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<RateResponse>> findAllRate() {
        return ResponseEntity.status(HttpStatus.OK).body(iRateService.findAllRate());
    }

    @Override
    public ResponseEntity<RateResponse> findByIdRate(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(iRateService.findRate(id));
    }
}

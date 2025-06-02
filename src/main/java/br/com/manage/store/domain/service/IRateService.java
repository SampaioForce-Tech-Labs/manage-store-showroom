package br.com.manage.store.domain.service;

import br.com.manage.store.application.api.request.RateRequest;
import br.com.manage.store.application.api.response.RateResponse;

import java.util.List;

public interface IRateService {
    RateResponse createRate(RateRequest request);
    RateResponse updateRate(Long id, RateRequest request);
    List<RateResponse> findAllRate();
    RateResponse findRate(Long id);
    void deleteRate(Long id);
}

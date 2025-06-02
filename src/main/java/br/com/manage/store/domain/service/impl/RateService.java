package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.RateRequest;
import br.com.manage.store.application.api.response.RateResponse;
import br.com.manage.store.domain.entity.RateEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.IRateService;
import br.com.manage.store.infrastructure.repository.RateRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RateService implements IRateService {
    private final RateRepository rateRepository;
    private final GenericMapper mapper;
    @Override
    public RateResponse createRate(RateRequest request) {
        var entity = rateRepository.save(mapper.map(request, RateEntity.class));
        return mapper.map(entity, RateResponse.class);
    }

    @Override
    public RateResponse updateRate(Long id, RateRequest request) {
        var entityFind = rateRepository.findById(id).orElseThrow(() -> new NullPointerException("Taxa não localizada na base de dados."));
        BeanUtils.copyProperties(request, entityFind, "id");
        var entitySave = rateRepository.save(entityFind);
        return mapper.map(entitySave, RateResponse.class);
    }

    @Override
    public List<RateResponse> findAllRate() {
        return mapper.mapAll(rateRepository.findAll(), RateResponse.class);
    }

    @Override
    public RateResponse findRate(Long id) {
        return mapper.map(rateRepository.findById(id), RateResponse.class);
    }

    @Override
    public void deleteRate(Long id) {
        rateRepository.deleteById(id);
    }
}

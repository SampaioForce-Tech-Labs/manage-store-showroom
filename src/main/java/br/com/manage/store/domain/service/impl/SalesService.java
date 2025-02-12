package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.SalesCalcRequest;
import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.SalesCalcResponse;
import br.com.manage.store.application.api.response.SalesResponse;
import br.com.manage.store.domain.entity.SalesEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.ISalesService;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import br.com.manage.store.infrastructure.repository.SalesProductRepository;
import br.com.manage.store.infrastructure.repository.SalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.Collectors;

import static br.com.manage.store.infrastructure.util.VerifyNotNullUtils.notNull;

@Service
@AllArgsConstructor
public class SalesService implements ISalesService {

    private final SalesRepository salesRepository;
    private final SalesProductRepository salesProductRepository;
    private final CustomerRepository customerRepository;
    private GenericMapper mapper;

    @Transactional
    @Override
    public SalesResponse create(SalesRequest request) {
        notNull(request.getCustomer());

        var entity = mapper.map(request, SalesEntity.class);

        var customer = customerRepository.findByCpf(request.getCustomer()).orElseThrow(() -> new NotFoundException("Cliente: " + request.getCustomer()));
        entity.setCustomerEntity(customer);
        entity.getSalesProductEntities().forEach(rest -> rest.setSalesEntity(entity));
        return null;
//        return mapper.map(salesRepository.save(entity), SalesResponse.class);
    }

    @Override
    public SalesResponse findById(Long id) {
        notNull(id);
        return mapper.map(salesRepository.findById(id).get(), SalesResponse.class);
    }

    @Override
    public void delete(Long id) {
        notNull(id);
    }

    @Transactional
    @Override
    public SalesResponse update(Long id, SalesRequest request) {
        notNull(id, request);
        return null;
    }

    @Override
    public Page<SalesResponse> findAll(Specification<SalesEntity> specification, int size, int page) {
        return null;
    }

    @Override
    public SalesCalcResponse salesCalc(SalesCalcRequest request) {
        var listprice = request.getSalesProductRequests().stream().map(price -> price.getPrice()).collect(Collectors.toList());
        BigDecimal priceTotal = listprice.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal priceDiscount = priceTotal.multiply(BigDecimal.valueOf(request.getDiscount() / 100));
        return new SalesCalcResponse(request.getSalesProductRequests().size(), request.getDiscount(), priceDiscount.setScale(2, RoundingMode.HALF_UP), priceTotal.subtract(priceDiscount).setScale(2, RoundingMode.HALF_UP));
    }
}

package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.SalesCalcRequest;
import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.ProductResponse;
import br.com.manage.store.application.api.response.SalesCalcResponse;
import br.com.manage.store.application.api.response.SalesResponse;
import br.com.manage.store.domain.entity.SalesEntity;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.ISalesService;
import br.com.manage.store.infrastructure.component.ProductExists;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import br.com.manage.store.infrastructure.repository.SalesProductRepository;
import br.com.manage.store.infrastructure.repository.SalesRepository;
import br.com.manage.store.infrastructure.util.CalcPriceUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static br.com.manage.store.infrastructure.util.VerifyNotNullUtils.notNull;

@Service
@AllArgsConstructor
public class SalesService implements ISalesService {

    private final SalesRepository salesRepository;
    private final SalesProductRepository salesProductRepository;
    private final CustomerRepository customerRepository;
    private final ProductExists productExists;
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

        var productEntityMap = productExists.verifyProductAndMap(request.getSalesProductRequest());

        var price = request.getSalesProductRequest().stream().map(ref -> productEntityMap.get(ref.getCode()).getPrice().multiply(BigDecimal.valueOf(ref.getAmount()))).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.DOWN);
        var priceWithDiscount = CalcPriceUtil.discountAdditional(request, productEntityMap);
        var totalDiscount = price.subtract(priceWithDiscount).setScale(2, RoundingMode.DOWN);
        return new SalesCalcResponse(request.getSalesProductRequest().stream().mapToInt(ref -> ref.getAmount()).sum(), request.getDiscount(), priceWithDiscount, price, totalDiscount, mapper.mapAll(productEntityMap.values().stream().toList(), ProductResponse.class));
    }
}

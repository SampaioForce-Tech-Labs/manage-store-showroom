package br.com.manage.store.domain.service.impl;

import br.com.manage.store.application.api.request.SalesCalcRequest;
import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.application.api.response.SalesCalcResponse;
import br.com.manage.store.application.api.response.SalesResponse;
import br.com.manage.store.domain.entity.SalesEntity;
import br.com.manage.store.domain.entity.SalesProductEntity;
import br.com.manage.store.domain.enums.StatusEnum;
import br.com.manage.store.domain.mapper.GenericMapper;
import br.com.manage.store.domain.service.ISalesService;
import br.com.manage.store.infrastructure.repository.CustomerRepository;
import br.com.manage.store.infrastructure.repository.ProductRepository;
import br.com.manage.store.infrastructure.repository.SalesProductRepository;
import br.com.manage.store.infrastructure.repository.SalesRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static br.com.manage.store.infrastructure.util.DecrementSalesProductUtils.decrementProduct;
import static br.com.manage.store.infrastructure.util.VerifyNotNullUtils.notNull;

@Service
@AllArgsConstructor
public class SalesService implements ISalesService {

    private final SalesRepository salesRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private GenericMapper mapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SalesResponse create(SalesRequest request) {
        decrementProduct(productRepository, request);
        var sales = mapper.map(request, SalesEntity.class);
        sales.setCustomerEntity(customerRepository.findByCpf(request.getCustomerCpf()).get());
        sales.setStatus(StatusEnum.FINALIZADO);

        var salesProductEntitys = mapper.mapAll(request.getProducts(), SalesProductEntity.class);
        sales.setProductEntities(salesProductEntitys);


        sales.getProductEntities().forEach(product -> product.setSalesEntity(sales));

        sales.setProductEntities(salesProductEntitys);

        var salesEntity = salesRepository.save(sales);
        return mapper.map(salesEntity, SalesResponse.class);
    }


    @Override
    public SalesResponse findById(Long id) {
        notNull(id);
        return mapper.map(salesRepository.findById(id).get(), SalesResponse.class);
    }

    @Override
    public void delete(Long id) {
        notNull(id);
        salesRepository.findById(id);
    }

    @Transactional
    @Override
    public SalesResponse update(Long id, SalesRequest request) {
        notNull(id, request);
        SalesEntity salesEntity = mapper.map(request, SalesEntity.class);
        return mapper.map(salesEntity, SalesResponse.class);
    }

    @Override
    public Page<SalesResponse> findAll(Specification<SalesEntity> specification, int size, int page) {
        return null;
    }

    @Override
    public SalesCalcResponse salesCalc(SalesCalcRequest request) {
        return null;
    }
//
//    @Override
//    public Page<SalesResponse> findAll(Specification<SalesEntity> specification, int size, int page) {
//        return null;
//    }
}

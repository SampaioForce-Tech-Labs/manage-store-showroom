package br.com.manage.store.infrastructure.util;

import br.com.manage.store.application.api.request.SalesRequest;
import br.com.manage.store.infrastructure.handler.exceptions.InsufficientStockException;
import br.com.manage.store.infrastructure.repository.ProductRepository;

import java.util.stream.Collectors;


public class DecrementSalesProductUtils {

    public static void  decrementProduct(ProductRepository productRepository,SalesRequest request){
        var products = request.getProducts().stream().map(productsL ->{
            var producEntity = productRepository.findByCode(productsL.getCode()).get();
            if (productsL.getQuantityInStock() > producEntity.getAmount()){
                throw new InsufficientStockException();
            }
            producEntity.setAmount(producEntity.getAmount() - productsL.getQuantityInStock());
            return producEntity;
        }).collect(Collectors.toList());
        productRepository.saveAll(products);
    }
}

package br.com.manage.store.domain.util;

import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ComparePrice {

    public void checkPrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidArgumentException("PRICE " + price);
        }
    }
}

package br.com.manage.store.infrastructure.util;

import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ComparePrice {

    public static void checkPrice(BigDecimal price){
        if (price.compareTo(BigDecimal.ZERO) < 0){
            throw new InvalidArgumentException("PREÇO R$ " + price);
        }
    }
}

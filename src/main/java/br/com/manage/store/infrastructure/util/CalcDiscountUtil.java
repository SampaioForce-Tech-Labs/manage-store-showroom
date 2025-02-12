package br.com.manage.store.infrastructure.util;

import java.math.BigDecimal;

public class CalcDiscountUtil {

    public static BigDecimal discount(double percentage, BigDecimal price) {
        if (percentage <= 0 || price.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return price.subtract(price.multiply((BigDecimal.valueOf(percentage / 100))));
    }
}

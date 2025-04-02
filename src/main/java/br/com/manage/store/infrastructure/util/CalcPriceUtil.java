package br.com.manage.store.infrastructure.util;

import br.com.manage.store.application.api.request.SalesCalcRequest;
import br.com.manage.store.domain.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalcPriceUtil {

    public static BigDecimal discount(double percentage, BigDecimal price) {
        if (percentage <= 0 || price.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        return price.subtract(price.multiply((BigDecimal.valueOf(percentage / 100))));
    }

    public static BigDecimal discountAdditional(SalesCalcRequest request, Map<String, ProductEntity> productEntityMap) {
        return request.getSalesProductRequest().stream().map(ref -> {
            var productEntity = productEntityMap.get(ref.getCode());
            var percentageDiscount = productEntity.getDiscountPercentage();
            if (percentageDiscount == 0) {
                return discount(request.getDiscount(), productEntity.getPrice()).multiply(BigDecimal.valueOf(ref.getQuantityInStock())).setScale(2, RoundingMode.DOWN);
            }
            return productEntity.getPriceWithDiscount().multiply(BigDecimal.valueOf(ref.getQuantityInStock()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

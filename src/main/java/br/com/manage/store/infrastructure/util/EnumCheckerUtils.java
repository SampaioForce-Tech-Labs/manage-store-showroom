package br.com.manage.store.infrastructure.util;

import br.com.manage.store.application.api.request.ProductRequest;
import br.com.manage.store.domain.enums.category.CategoryEnum;
import br.com.manage.store.infrastructure.handler.exceptions.IllegalEnumException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.stream.Collectors;

import static br.com.manage.store.infrastructure.util.ConvertReplaceAllUtils.replaceAll;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumCheckerUtils {

    public static void isValidEnum(ProductRequest request) {
        try {
            var category = CategoryEnum.valueOf(replaceAll(request.getCategory()));
            if (Arrays.stream(category.getSubcategory())
                    .filter(f -> f.getSubcategory().contains(request.getSubcategory()))
                    .collect(Collectors.toList()).isEmpty()) {
                throw new IllegalEnumException();
            }
        } catch (IllegalArgumentException ex) {
            throw new IllegalEnumException();
        }
    }
}

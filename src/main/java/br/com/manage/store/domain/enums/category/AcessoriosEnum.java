package br.com.manage.store.domain.enums.category;

import br.com.manage.store.domain.enums.ISubcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AcessoriosEnum implements ISubcategory {
    BOLSAS("Bolsa"),
    CARTEIRAS("Cateiras"),
    CINTOS("Cintos"),
    OCULOS("Ó culos"),
    SEMIJOIAS("Semijoias");

    private final String subcategory;
}

package br.com.manage.store.domain.enums.category;

import br.com.manage.store.domain.enums.ISubcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PerfumariaEnum implements ISubcategory {
    MARY_KEY("Mary Key"),
    NATURA("Natura"),
    OBOTICARIO("O Boticáário");

    private final String subcategory;
}

package br.com.manage.store.domain.enums.category;

import br.com.manage.store.domain.enums.ISubcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SapatosEnum implements ISubcategory {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    JUVENIL("Juvenil"),
    INFANTIL("Infantil"),
    SALTO("Salto"),
    SANDALHA("Sandalha");
    private final String subcategory;
}

package br.com.manage.store.domain.enums.category;

import br.com.manage.store.domain.enums.ISubcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoupasEnum implements ISubcategory {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    JUVENIL("Juvenil"),
    INFANTIL("Infantil");
    private final String subcategory;
}

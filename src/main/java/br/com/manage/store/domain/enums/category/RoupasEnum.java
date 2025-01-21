package br.com.manage.store.domain.enums.category;

import br.com.manage.store.domain.enums.ISubcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoupasEnum implements ISubcategory {
    MASCULINO("Masculino"),
    FEMININO("Feminina"),
    JUVENIL("Juvenil"),
    INFANTIL("Infantil"),
    MODAPRAIA("Moda praia"),
    CAMA("Cama"),
    MESA("Mesa"),
    BANHO("Banho");

    private final String subcategory;
}

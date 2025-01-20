package br.com.manage.store.domain.enums.category;

import br.com.manage.store.domain.enums.ISubcategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoryEnum {
    ROUPAS("Roupas", RoupasEnum.values()),
    ACESSORIOS("Acessórios", AcessoriosEnum.values()),
    PERFUMARIA("Perfumaria", PerfumariaEnum.values());

    private String category;
    private ISubcategory[] subcategory;
}

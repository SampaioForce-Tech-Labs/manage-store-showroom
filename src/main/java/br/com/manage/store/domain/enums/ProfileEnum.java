package br.com.manage.store.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ProfileEnum {

    BOM(1, "Bom"), MEDIO(2, "Médio"), RUIM(3, "Ruim");
    protected int id;
    protected String profile;
}

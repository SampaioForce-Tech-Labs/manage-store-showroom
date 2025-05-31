package br.com.manage.store.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    PENDENTE("Pendente"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private final String status;
}

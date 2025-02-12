package br.com.manage.store.application.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SalesCalcRequest {
    private Double discount;
    private List<SalesProductRequest> salesProductRequests;
}

package br.com.manage.store.application.api;

import br.com.manage.store.application.api.request.RateRequest;
import br.com.manage.store.application.api.response.RateResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("rates")
public interface IRateController {
    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RateResponse> createRate(@RequestBody RateRequest request);

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<RateResponse> updateRate(@PathVariable Long id, @RequestBody RateRequest request);

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> deleteRate(@PathVariable Long id);

    @GetMapping(value = "/find-all", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<RateResponse>> findAllRate();

    @GetMapping(value = "/find/{id}")
    ResponseEntity<RateResponse> findByIdRate(@PathVariable Long id);

}

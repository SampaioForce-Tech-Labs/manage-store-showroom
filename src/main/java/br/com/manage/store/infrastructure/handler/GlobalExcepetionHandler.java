package br.com.manage.store.infrastructure.handler;

import br.com.manage.store.infrastructure.component.MessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@Slf4j
@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExcepetionHandler extends ResponseEntityExceptionHandler {


    private final MessageService messageService;

    protected ResponseEntity<Object> handlerException(Exception exception, HttpStatus status, WebRequest request, String key, Object[] args) {
        ApiError<List<String>> response = new ApiError<>(List.of((messageService.getMessage(key, args))));
        response.setStatusCode(status.value());
        response.setPath(request.getContextPath());
        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }
}

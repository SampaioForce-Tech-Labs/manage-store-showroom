package br.com.manage.store.infrastructure.handler;

import br.com.manage.store.infrastructure.component.MessageService;
import br.com.manage.store.infrastructure.handler.exceptions.ConflictException;
import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import br.com.manage.store.infrastructure.handler.exceptions.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;


@Slf4j
@ControllerAdvice
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExcepetionHandler extends ResponseEntityExceptionHandler {

    private final MessageService messageService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<String> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ApiError<List<String>> response = new ApiError<>(errors);
        response.setStatusCode(status.value());
        response.setPath(request.getDescription(false));

        return handleExceptionInternal(exception, response, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(
            DataIntegrityViolationException exception,
            WebRequest request) {

        String key = "error.violation.optional";
        Object[] args = {exception.getMessage()};

        return handlerException(exception, HttpStatus.INTERNAL_SERVER_ERROR, request, key, args);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(
            NotFoundException exception,
            WebRequest request) {

        String key = "error.notfound.argument";
        Object[] args = {exception.getMessage()};

        return handlerException(exception, HttpStatus.NOT_FOUND, request, key, args);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<Object> handleInvalidArgumentException(
            InvalidArgumentException exception,
            WebRequest request) {

        String key = "error.invalid.argument";
        Object[] args = {exception.getMessage()};

        return handlerException(exception, HttpStatus.BAD_REQUEST, request, key, args);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(
            ConflictException exception,
            WebRequest request,
            HttpServletRequest http) {

        String key = "error.conflict.value";
        Object[] args = {exception.getMessage()};

        return handlerException(exception, HttpStatus.BAD_REQUEST, request, key, args);
    }

    protected ResponseEntity<Object> handlerException(Exception exception, HttpStatus status, WebRequest request, String key, Object[] args) {

        ApiError<List<String>> response = new ApiError<>(List.of((messageService.getMessage(key, args))));
        response.setStatusCode(status.value());
        response.setPath(request.getDescription(false));

        log.error("ERROR: {}", response);

        return handleExceptionInternal(exception, response, new HttpHeaders(), status, request);
    }
}

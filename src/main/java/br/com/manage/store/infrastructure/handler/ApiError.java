package br.com.manage.store.infrastructure.handler;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiError<T> implements Serializable {


    private List<String> errors = new ArrayList<>();
    private String timestamp;
    private int statusCode;
    private String path;


    public ApiError(List<String> errors) {
        super();
        this.errors = errors;
    }

    public String getTimestamp() {
        LocalDateTime dataAndHora = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        timestamp = dateTimeFormatter.format(dataAndHora);
        return timestamp;
    }
}

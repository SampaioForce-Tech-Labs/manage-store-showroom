package br.com.manage.store.domain.util;

import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import org.springframework.stereotype.Component;

@Component
public class CheckNotNull {

    public void validation(Object... objs) {
        try {
            for (Object dados : objs) {
                if (dados == null) {
                    throw new InvalidArgumentException("VALUE: " + dados);
                }
            }
        }catch (Exception e){
            throw new InvalidArgumentException("VALUE: " + objs);
        }
    }
}

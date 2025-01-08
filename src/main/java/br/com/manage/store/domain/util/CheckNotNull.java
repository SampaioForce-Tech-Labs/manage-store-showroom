package br.com.manage.store.domain.util;

import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CheckNotNull {

    public static void validation(Object... objs) {
        try {
            for (Object deta : objs) {
                if (deta == null) {
                    throw new InvalidArgumentException("VALUE: " + deta);
                }
            }
        }catch (Exception e){
            throw new InvalidArgumentException("VALUE: " + objs);
        }
    }
}

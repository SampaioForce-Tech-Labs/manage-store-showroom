package br.com.manage.store.domain.util;

import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class VerifyNotNull {

    public static void notNull(Object... sources) {

        try {
            for (Object data : sources) {
                if (data == null) {
                    throw new InvalidArgumentException();
                }
            }
        } catch (Exception e) {
            throw new InvalidArgumentException();
        }
    }
}

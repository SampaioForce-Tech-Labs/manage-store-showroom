package br.com.manage.store.infrastructure.util;

import br.com.manage.store.infrastructure.handler.exceptions.InvalidArgumentException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VerifyNotNullUtils {

    public static void notNull(Object... sources) {

        try {
            for (Object data : sources) {
                if (ObjectUtils.isEmpty(data)) {
                    throw new InvalidArgumentException();
                }
            }
        } catch (Exception e) {
            throw new InvalidArgumentException();
        }
    }
}

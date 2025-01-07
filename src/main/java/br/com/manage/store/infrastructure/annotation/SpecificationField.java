package br.com.manage.store.infrastructure.annotation;



import br.com.manage.store.infrastructure.component.specification.SpecificationOperation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({ FIELD })
public @interface SpecificationField {

    String property() default "";
    
    String join() default "";

    SpecificationOperation operation() default SpecificationOperation.EQUAL;

}

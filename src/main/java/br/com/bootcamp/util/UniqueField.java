package br.com.bootcamp.util;

import br.com.bootcamp.util.UniqueFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueFieldValidator.class})
public @interface UniqueField {
    String message() default "Informação já existente";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    Class<?> entityName();

    String fieldName();
}

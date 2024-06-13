package teamchallenge.server.annotation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import teamchallenge.server.validators.UniqueEmailValidator;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
@Documented
public @interface UniqueEmail {
    String message() default "Email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

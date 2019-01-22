package com.example.hackyeah.validator.road;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {RoadDataInRangeValidator.class})
public @interface RoadDataInRange {
    String message() default "Road data not in range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

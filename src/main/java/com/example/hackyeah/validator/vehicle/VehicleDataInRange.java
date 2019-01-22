package com.example.hackyeah.validator.vehicle;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {VehicleDataInRangeValidator.class})
public @interface VehicleDataInRange {
    String message() default "Vehicle data not in range";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

package com.example.hackyeah.validator;

import com.example.hackyeah.entity.Road;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class RoadDataInRangeValidator implements ConstraintValidator<RoadDataInRange, Road> {
    @Override
    public boolean isValid(Road road, ConstraintValidatorContext constraintValidatorContext) {
        return (Optional.ofNullable(road.getHeight()).orElse(0d) > 0) &&
                (Optional.ofNullable(road.getWidth()).orElse(0d) > 0);
    }
}

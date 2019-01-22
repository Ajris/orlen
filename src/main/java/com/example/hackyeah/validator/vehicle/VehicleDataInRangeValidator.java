package com.example.hackyeah.validator.vehicle;

import com.example.hackyeah.entity.Vehicle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class VehicleDataInRangeValidator implements ConstraintValidator<VehicleDataInRange, Vehicle> {
    @Override
    public boolean isValid(Vehicle vehicle, ConstraintValidatorContext constraintValidatorContext) {
        return (Optional.ofNullable(vehicle.getHeight()).orElse(0d) > 0) &&
                (Optional.ofNullable(vehicle.getWidth()).orElse(0d) > 0);
    }
}

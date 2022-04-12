/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hugo_test.validation;

import hugo_test.errors.ValidationError;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author JesusHdezWaterloo@Github
 */
public class Validation {

    //Created static to avoid recreated every time a validation occur
    private static final Validator DEFAULT_VALIDATOR = javax.validation.Validation.buildDefaultValidatorFactory().getValidator();

    public static Set<ConstraintViolation<Object>> validate(Object object) {
        return DEFAULT_VALIDATOR.validate(object);
    }

    public static void validateAndThrow(Object object) throws ValidationError {
        Set<ConstraintViolation<Object>> errors = DEFAULT_VALIDATOR.validate(object);
        if (!errors.isEmpty()) {
            throw new ValidationError(errors.iterator().next().getMessage());
        }
    }
}

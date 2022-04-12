/*
 * Copyright 2022 Root101 (jhernandezb96@gmail.com, +53-5-426-8660).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Or read it directly from LICENCE.txt file at the root of this project.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hugo_test.validation;

import hugo_test.errors.ValidationError;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
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

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
package hugo_test.errors;

import hugo_test.errors.Error.ErrorMessage;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseEntity<Object> handleConflict(
            Exception ex, WebRequest request) throws Exception {

        //si la exception esta anotada con el ResponseStatus, Spring se encarga de convertirla
        if (AnnotationUtils.findAnnotation(ex.getClass(), ResponseStatus.class) != null) {
            throw ex;
        } else if (ex instanceof ApiException apiExc) {
            //si es un error interno la convierto con el codigo
            return new ResponseEntity(
                    new ErrorMessage(
                            ex.getMessage()
                    ),
                    apiExc.status()
            );
        } else {
            //cualquier otra cosa es 500, lo convierto
            return new ResponseEntity(
                    new ErrorMessage(
                            ex.getMessage()
                    ),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

}

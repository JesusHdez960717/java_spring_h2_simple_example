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
package hugo_test.domain;

import hugo_test.validation.Password;
import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class Domains {

    public record HugoNewUser(
            @NotEmpty(message = "name can't be empty")
            @Size(max = 100, message = "name too long")
            String name,
            @Email(message = "wrong e-mail.")//no hay necesidad de usar regex cuando lo hace solo
            @NotEmpty(message = "e-mail can't be empty")
            @Size(max = 100, message = "e-mail too long")
            String email,
            @Password(message = "weak passwork")
            @NotEmpty(message = "password can't be empty")
            @Size(max = 100, message = "password too long")
            String password,
            List<HugoNewPhone> phones) {

    }

    public record HugoNewPhone(
            @NotEmpty(message = "cell number can't be empty")
            @Size(max = 25, message = "cell number too long")
            String number,
            @NotEmpty(message = "city code can't be empty")
            @Size(max = 10, message = "city code too long")
            String citycode,
            @NotEmpty(message = "country code can't be empty")
            @Size(max = 10, message = "country code too long")
            String contrycode) {

    }

    public record HugoCreatedPhone(
            String number,
            String citycode,
            String contrycode) {

    }

    public record HugoCreatedUser(
            String id,
            String name,
            String email,
            LocalDateTime created,
            LocalDateTime lastLogin,
            boolean isActive,
            String token,
            List<HugoCreatedPhone> phones) {

    }

}

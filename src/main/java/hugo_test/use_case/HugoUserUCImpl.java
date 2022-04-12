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
package hugo_test.use_case;

import hugo_test.domain.Domains.*;
import hugo_test.entities.*;
import hugo_test.errors.AlreadyRegisteredUser;
import hugo_test.errors.ValidationError;
import hugo_test.repo.*;
import hugo_test.validation.Validation;
import java.util.*;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Component
public class HugoUserUCImpl implements HugoUserUC {

    @Autowired
    private HugoPhoneRepo repoPhone;

    @Autowired
    private HugoUserRepo repoUser;

    @Override
    public Iterable<HugoUser> findAllUsers() throws RuntimeException {
        return repoUser.findAll();
    }

    @Override
    public HugoCreatedUser createUser(HugoNewUser newUser) throws RuntimeException {
        //convierto el usuario y genero los UID
        HugoUser hugoUser = HugoUserConverter.instance().convertUser(newUser);

        //convierto los telefonos a los nuevos, con el id del usuario
        List<HugoPhone> phones = HugoUserConverter.instance().convertPhones(newUser.phones(), hugoUser);

        //validar lo que me entran
        Validation.validateAndThrow(hugoUser);
        //valiad pass antes del hash
        if (!Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])$", newUser.password())) {
            throw new ValidationError("Contraseña debil");
        }
        phones.forEach((phone) -> {
            Validation.validateAndThrow(phone);
        });
        if (repoUser.findByEmail(hugoUser.getEmail()) != null) {
            throw new AlreadyRegisteredUser(hugoUser.getEmail());
        }

        //persisto la informacion
        repoUser.save(hugoUser);
        repoPhone.saveAll(phones);

        //devuelvo la informacion nueva generada
        return HugoUserConverter.instance().convertCreatedUser(hugoUser, phones);
    }

}

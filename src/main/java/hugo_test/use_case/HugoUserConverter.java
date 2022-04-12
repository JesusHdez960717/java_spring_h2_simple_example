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

import hugo_test.domain.Domains;
import hugo_test.entities.HugoPhone;
import hugo_test.entities.HugoUser;
import hugo_test.utils.SHA256;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
public class HugoUserConverter {

    private static HugoUserConverter INSTANCE = new HugoUserConverter();

    public static HugoUserConverter instance() {
        return INSTANCE;
    }

    private HugoUserConverter() {
    }

    public Domains.HugoCreatedUser convertCreatedUser(HugoUser hugoUser, List<HugoPhone> phones) {
        return new Domains.HugoCreatedUser(
                hugoUser.getIdUuid(),
                hugoUser.getName(),
                hugoUser.getEmail(),
                hugoUser.getLastLogin(),
                hugoUser.getLastLogin(),
                hugoUser.isIsActive(), hugoUser.getToken(),
                convertCreatedPhones(phones)
        );
    }

    public List<Domains.HugoCreatedPhone> convertCreatedPhones(List<HugoPhone> phones) {
        return phones.stream().map((phone) -> {
            return new Domains.HugoCreatedPhone(
                    phone.getNumber(),
                    phone.getCityCode(),
                    phone.getCountryCode()
            );
        }).collect(Collectors.toList());
    }

    public HugoUser convertUser(Domains.HugoNewUser newUser) {
        return new HugoUser(
                UUID.randomUUID().toString(),
                newUser.name(),
                newUser.email(),
                SHA256.hash(newUser.password()),
                LocalDateTime.now(),
                LocalDateTime.now(),
                true,
                UUID.randomUUID().toString()
        );
    }

    public List<HugoPhone> convertPhones(List<Domains.HugoNewPhone> phones, HugoUser hugoUser) {
        return phones.stream().map((newPhone) -> {
            return new HugoPhone(
                    UUID.randomUUID().toString(),
                    newPhone.number(),
                    newPhone.citycode(),
                    newPhone.contrycode(),
                    hugoUser
            );
        }).collect(Collectors.toList());
    }
}

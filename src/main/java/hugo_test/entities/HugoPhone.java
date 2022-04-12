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
package hugo_test.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Entity
@Table(name = "HUGO_PHONE")
public class HugoPhone implements Serializable {

    @Id
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID_UUID", nullable = false)
    private String idUuid;

    @Size(min = 1, max = 25)
    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Size(min = 1, max = 10)
    @Column(name = "CITY_CODE", nullable = false)
    private String cityCode;

    @Size(min = 1, max = 10)
    @Column(name = "COUNTRY_CODE", nullable = false)
    private String countryCode;

    @JoinColumn(name = "USER_ID", referencedColumnName = "ID_UUID", nullable = false)
    @ManyToOne(optional = false)
    private HugoUser userId;

    public HugoPhone(String idUuid, String number, String cityCode, String countryCode, HugoUser userId) {
        this.idUuid = idUuid;
        this.number = number;
        this.cityCode = cityCode;
        this.countryCode = countryCode;
        this.userId = userId;
    }

    public HugoPhone() {
    }

    public String getIdUuid() {
        return idUuid;
    }

    public void setIdUuid(String idUuid) {
        this.idUuid = idUuid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public HugoUser getUserId() {
        return userId;
    }

    public void setUserId(HugoUser userId) {
        this.userId = userId;
    }

}

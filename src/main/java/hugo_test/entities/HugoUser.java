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
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 *
 * @author Root101 (jhernandezb96@gmail.com, +53-5-426-8660)
 * @author JesusHdezWaterloo@Github
 */
@Entity
@Table(name = "HUGO_USER")
public class HugoUser implements Serializable {

    @Id
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "ID_UUID", nullable = false)
    private String idUuid;

    @Size(min = 1, max = 255)
    @Column(name = "NAME", nullable = false)
    private String name;

    @Email//no hay necesidad de usar regex cuando lo hace solo
    @Size(min = 1, max = 100)
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Size(min = 1, max = 64)
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull
    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;

    @NotNull
    @Column(name = "LAST_LOGIN", nullable = false)
    private LocalDateTime lastLogin;

    @Column(name = "IS_ACTIVE", nullable = false)
    private boolean isActive;

    @Size(min = 1, max = 36)
    @Column(name = "TOKEN", nullable = false)
    private String token;

    public HugoUser() {
    }

    public HugoUser(String idUuid, String name, String email, String password, LocalDateTime created, LocalDateTime lastLogin, boolean isActive, String token) {
        this.idUuid = idUuid;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.token = token;
    }

    public String getIdUuid() {
        return idUuid;
    }

    public void setIdUuid(String idUuid) {
        this.idUuid = idUuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "HugoUser{" + "idUuid=" + idUuid + ", name=" + name + ", email=" + email + ", password=" + password + ", created=" + created + ", lastLogin=" + lastLogin + ", isActive=" + isActive + ", token=" + token + '}';
    }

}

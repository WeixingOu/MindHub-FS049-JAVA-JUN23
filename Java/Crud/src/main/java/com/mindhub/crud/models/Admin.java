package com.mindhub.crud.models;

import com.mindhub.crud.enums.Gender;
import com.mindhub.crud.enums.Role;
import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Admin extends Users {
    private LocalDateTime lastLogin;

    public Admin() {}

    public Admin(String firstName, String lastName, String email, String password, LocalDate birthdate, LocalDateTime createdDate, Gender gender, Role role, boolean active, LocalDateTime lastLogin) {
        super(firstName, lastName, email, password, birthdate, createdDate, gender, role, active);
        this.lastLogin = lastLogin;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }
}

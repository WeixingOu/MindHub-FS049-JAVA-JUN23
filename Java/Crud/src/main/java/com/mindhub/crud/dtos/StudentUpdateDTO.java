package com.mindhub.crud.dtos;

import com.mindhub.crud.enums.Gender;
import com.mindhub.crud.models.Student;

import java.time.LocalDate;
import java.util.Date;

public class StudentUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private Gender gender;
    private String education;

    public StudentUpdateDTO(Student student) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.birthdate = student.getBirthdate();
        this.gender = student.getGender();
        this.education = student.getEducation();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEducation() {
        return education;
    }
}

package com.mindhub.crud.dtos;

import com.mindhub.crud.enums.Gender;
import com.mindhub.crud.models.Professor;
import com.mindhub.crud.models.Student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ProfessorDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private Gender gender;
    private String bio;
    private Set<ProfessorCourseDTO> studentCourses;

    private Map<String, Object> newProperty = new HashMap<>();

    public ProfessorDTO() {}
    public ProfessorDTO(Professor professor) {
        this.firstName = professor.getFirstName();
        this.lastName = professor.getLastName();
        this.email = professor.getEmail();
        this.birthdate = professor.getBirthdate();
        this.gender = professor.getGender();
        this.bio = professor.getBio();
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
        return bio;
    }

    public String getBio() {
        return bio;
    }

    public Set<ProfessorCourseDTO> getStudentCourses() {
        return studentCourses;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setStudentCourses(Set<ProfessorCourseDTO> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public void addNewProperty(String key, Object value) {
        this.newProperty.put(key, value);
    }

    public Map<String, Object> getNewProperty() {
        return this.newProperty;
    }
}

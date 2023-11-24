package com.mindhub.crud.dtos;

import com.mindhub.crud.enums.Gender;
import com.mindhub.crud.models.Student;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StudentDTO {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private Gender gender;
    private String education;
    private Set<StudentCourseDTO> studentCourses;

    private Map<String, Object> newProperty = new HashMap<>();

    public StudentDTO() {}
    public StudentDTO(Student student) {
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        this.birthdate = student.getBirthdate();
        this.gender = student.getGender();
        this.education = student.getEducation();
        this.studentCourses = student.getStudentCourses().stream().map(StudentCourseDTO::new).collect(Collectors.toSet());
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

    public Set<StudentCourseDTO> getStudentCourses() {
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

    public void setEducation(String education) {
        this.education = education;
    }

    public void setStudentCourses(Set<StudentCourseDTO> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public void addNewProperty(String key, Object value) {
        this.newProperty.put(key, value);
    }

    public Map<String, Object> getNewProperty() {
        return this.newProperty;
    }
}

package com.mindhub.crud.models;

import com.mindhub.crud.enums.Gender;
import com.mindhub.crud.enums.Role;
import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Entity
public class Student extends Users {
    private String education;
    @OneToMany(mappedBy = "student", fetch= FetchType.EAGER)
    private Set <StudentCourse> studentCourses = new HashSet<>();

    public Student() {}

    public Student(String firstName, String lastName, String email, String password, LocalDate birthdate, LocalDateTime createdDate, Gender gender, Role role, boolean active, String education) {
        super(firstName, lastName, email, password, birthdate, createdDate, gender, role, active);
        this.education = education;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public Set<Course> getCourses() {
        return studentCourses.stream().map(StudentCourse::getCourse).collect(toSet());
    }

    public void addStudentCourse(StudentCourse studentCourse) {
        studentCourse.setStudent(this);
        studentCourses.add(studentCourse);
    }
}

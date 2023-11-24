package com.mindhub.crud.models;

import com.mindhub.crud.enums.Gender;
import com.mindhub.crud.enums.Role;
import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Professor extends Users {
    private String bio;

    @OneToMany(mappedBy = "professor", fetch= FetchType.EAGER)
    private Set <ProfessorCourse> professorCourses = new HashSet<>();

    public Professor() {}

    public Professor(String firstName, String lastName, String email, String password, LocalDate birthdate, LocalDateTime createdDate, Gender gender, Role role, boolean active, String bio) {
        super(firstName, lastName, email, password, birthdate, createdDate, gender, role, active);
        this.bio = bio;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<ProfessorCourse> getProfessorCourses() {
        return professorCourses;
    }

    public void setProfessorCourses(Set<ProfessorCourse> professorCourses) {
        this.professorCourses = professorCourses;
    }

    public void addProfessorCourse(ProfessorCourse professorCourse) {
        professorCourse.setProfessor(this);
        professorCourses.add(professorCourse);
    }

    public void removeProfessorCourse(ProfessorCourse professorCourse) {
        this.professorCourses.remove(professorCourse);
        professorCourse.setProfessor(null);
    }
}

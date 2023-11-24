package com.mindhub.crud.models;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "professor", fetch= FetchType.EAGER)
    private Set<ProfessorCourse> professorCourses = new HashSet<>();

    @OneToMany(mappedBy = "course", fetch= FetchType.EAGER)
    private Set <StudentCourse> studentCourses = new HashSet<>();

    public Course() {}

    public Course(String name, String description, Set<ProfessorCourse> professorCourses, Set<StudentCourse> studentCourses) {
        this.name = name;
        this.description = description;
        this.professorCourses = professorCourses;
        this.studentCourses = studentCourses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ProfessorCourse> getProfessorCourses() {
        return professorCourses;
    }

    public void setProfessorCourses(Set<ProfessorCourse> professorCourses) {
        this.professorCourses = professorCourses;
    }

    public Set<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(Set<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}

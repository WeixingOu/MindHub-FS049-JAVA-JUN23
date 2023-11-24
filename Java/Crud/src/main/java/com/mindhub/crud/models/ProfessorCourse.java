package com.mindhub.crud.models;

import com.mindhub.crud.enums.Shift;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ProfessorCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private Shift shift;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="professor_id")
    private Professor professor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="course_id")
    private Course course;

    public ProfessorCourse() {}
    public ProfessorCourse(Shift shift, Professor professor, Course course) {
        this.shift = shift;
        this.professor = professor;
        this.course = course;
    }

    public Long getId() {
        return id;
    }
    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}

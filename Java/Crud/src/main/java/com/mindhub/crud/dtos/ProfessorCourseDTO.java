package com.mindhub.crud.dtos;

import com.mindhub.crud.enums.Shift;
import com.mindhub.crud.models.ProfessorCourse;

import java.time.LocalDate;

public class ProfessorCourseDTO {
    private Long id;
    private Long courseId;
    private Long professorId;
    private Shift shift;

    public ProfessorCourseDTO(ProfessorCourse professorCourse) {
        this.id = professorCourse.getId();
        this.courseId = professorCourse.getCourse().getId();
        this.professorId = professorCourse.getProfessor().getId();
        this.shift = professorCourse.getShift();
    }

    public ProfessorCourseDTO() {

    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public Shift getShift() {
        return shift;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

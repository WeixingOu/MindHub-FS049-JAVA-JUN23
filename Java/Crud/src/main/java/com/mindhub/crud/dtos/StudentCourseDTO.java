package com.mindhub.crud.dtos;

import com.mindhub.crud.enums.Shift;
import com.mindhub.crud.models.StudentCourse;

import java.time.LocalDate;

public class StudentCourseDTO {
    private Long id;
    private Long courseId;
    private Long studentId;
    private LocalDate enrolledDate;
    private Double grade;
    private Shift shift;

    public StudentCourseDTO(StudentCourse studentCourse) {
        this.id = studentCourse.getId();
        this.courseId = studentCourse.getCourse().getId();
        this.studentId = studentCourse.getStudent().getId();
        this.enrolledDate = studentCourse.getEnrolledDate();
        this.grade = studentCourse.getGrade();
        this.shift = studentCourse.getShift();
    }

    public StudentCourseDTO() {

    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public LocalDate getEnrolledDate() {
        return enrolledDate;
    }

    public Double getGrade() {
        return grade;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setEnrolledDate(LocalDate enrolledDate) {
        this.enrolledDate = enrolledDate;
    }

    public void setGrade(Double grade) {
        this.grade = grade;
    }

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}

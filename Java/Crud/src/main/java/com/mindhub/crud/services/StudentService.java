package com.mindhub.crud.services;

import com.mindhub.crud.dtos.StudentDTO;
import com.mindhub.crud.dtos.StudentUpdateDTO;
import com.mindhub.crud.models.Student;

import java.util.List;

public interface StudentService {
    List<StudentDTO> getStudents();
    Student getStudentById(Long id);
    Student getStudentByEmail(String email);
    StudentDTO createStudent(Student student);
    StudentDTO updateStudent(Student student);
    StudentDTO deleteStudent(Long id);
    boolean existsByEmail(String email);
    List<StudentDTO> getStudentsByNameContainAndCourseId(String name, Long courseId);
}

package com.mindhub.crud.services.impls;

import com.mindhub.crud.dtos.StudentDTO;
import com.mindhub.crud.dtos.StudentUpdateDTO;
import com.mindhub.crud.models.Student;
import com.mindhub.crud.repositories.StudentRepository;
import com.mindhub.crud.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDTO> getStudents() {
        return studentRepository.findByActiveTrue().stream().map(StudentDTO::new).collect(toList());
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findByIdAndActiveTrue(id).orElse(null);
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentRepository.findByEmailAndActiveTrue(email).orElse(null);
    }

    @Override
    public StudentDTO createStudent(Student student) {
        Student newStudent = studentRepository.save(student);
        return new StudentDTO(newStudent);
    }

    @Override
    public StudentDTO updateStudent(Student student) {
        Student studentUpt = studentRepository.save(student);
        return new StudentDTO(studentUpt);
    }

    @Override
    public StudentDTO deleteStudent(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();

            student.setActive(false);
            Student studentDlt = studentRepository.save(student);

            return new StudentDTO(studentDlt);
        }
        return null;
    }

    @Override
    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public List<StudentDTO> getStudentsByNameContainAndCourseId(String name, Long courseId) {
        return studentRepository.findByFirstNameContainingAndStudentCoursesCourseIdAndActiveTrue(name, courseId).stream().map(StudentDTO::new).collect(toList());
    }
}

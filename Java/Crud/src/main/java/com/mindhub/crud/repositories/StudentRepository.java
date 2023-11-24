package com.mindhub.crud.repositories;

import com.mindhub.crud.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByActiveTrue();
    Optional<Student> findByIdAndActiveTrue(Long id);
    Optional<Student> findByEmailAndActiveTrue(String email);
    Boolean existsByEmail(String email);
    List<Student> findByFirstNameContainingAndStudentCoursesCourseIdAndActiveTrue(String name, Long courseId);

}

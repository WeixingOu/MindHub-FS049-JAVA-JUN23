package com.mindhub.crud.repositories;

import com.mindhub.crud.models.Professor;
import com.mindhub.crud.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByIdAndActiveTrue(Long id);
    Optional<Professor> findByEmailAndActiveTrue(String email);
}

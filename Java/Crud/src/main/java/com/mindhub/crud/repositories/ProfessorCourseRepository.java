package com.mindhub.crud.repositories;

import com.mindhub.crud.models.ProfessorCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorCourseRepository extends JpaRepository<ProfessorCourse, Long> {
}

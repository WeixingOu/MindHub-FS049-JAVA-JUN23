package com.mindhub.crud.repositories;

import com.mindhub.crud.models.Course;
import com.mindhub.crud.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

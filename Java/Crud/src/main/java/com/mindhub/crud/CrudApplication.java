package com.mindhub.crud;

import com.mindhub.crud.enums.*;
import com.mindhub.crud.models.*;
import com.mindhub.crud.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootApplication
public class CrudApplication {
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(StudentRepository studentRepository, ProfessorRepository professorRepository, AdminRepository adminRepository, CourseRepository courseRepository, ProfessorCourseRepository professorCourseRepository, StudentCourseRepository studentCourseRepository) {
		return (args) -> {
			Student student1 = new Student("John", "Doe", "john.doe@example.com", passwordEncoder.encode("password1234"), LocalDate.of(1995, 5, 20), LocalDateTime.now(), Gender.MALE, Role.STUDENT, true, "Bachelor's Degree in Computer Science");
			studentRepository.save(student1);

			Professor professor1 = new Professor("Jane", "Smith", "jane.smith@example.com", passwordEncoder.encode("password5678"), LocalDate.of(1980, 6, 15), LocalDateTime.now(), Gender.FEMALE, Role.PROFESSOR, true, "Bio of Jane Smith");
			professorRepository.save(professor1);

			Admin admin1 = new Admin("Admin", "User", "admin@example.com", passwordEncoder.encode("admin1234"), LocalDate.of(1990, 1, 1), LocalDateTime.now(), Gender.MALE, Role.ADMIN, true, LocalDateTime.now());
			adminRepository.save(admin1);

			Course course1 = new Course();
			course1.setName("Introduction to Computer Science");
			course1.setDescription("This course provides an overview of the fundamental concepts of computer science.");
			courseRepository.save(course1);

			ProfessorCourse professorCourse1 = new ProfessorCourse(Shift.MORNING, professor1, course1);
			professorCourseRepository.save(professorCourse1);
			professor1.addProfessorCourse(professorCourse1);
			professorRepository.save(professor1);

			StudentCourse studentCourse1 = new StudentCourse();
			studentCourse1.setCourse(course1);
			studentCourse1.setStudent(student1);
			studentCourse1.setEnrolledDate(LocalDate.now());
			studentCourse1.setShift(Shift.MORNING);
			studentCourse1.setGrade(88.5);
			studentCourseRepository.save(studentCourse1);
			student1.addStudentCourse(studentCourse1);
			studentRepository.save(student1);
		};
	}
}
package com.mindhub.crud.controllers;

import com.mindhub.crud.dtos.StudentCourseDTO;
import com.mindhub.crud.dtos.StudentDTO;
import com.mindhub.crud.dtos.StudentUpdateDTO;
import com.mindhub.crud.enums.Shift;
import com.mindhub.crud.models.Course;
import com.mindhub.crud.models.Student;
import com.mindhub.crud.models.StudentCourse;
import com.mindhub.crud.services.CourseService;
import com.mindhub.crud.services.StudentService;
import com.mindhub.crud.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import static com.mindhub.crud.utils.UserUtils.*;
import static com.mindhub.crud.utils.StudentUtils.*;

@RestController
@RequestMapping("/api")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/students")
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@RequestBody Student student, Authentication authentication) {
        if (studentService.getStudentByEmail(authentication.getName()) == null) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorStudentDto));
        }

        if (!validateStudentFields(student)) {
            StudentDTO errorStudentDto = new StudentDTO();

            errorStudentDto.setFirstName(student.getFirstName());
            errorStudentDto.setLastName(student.getLastName());
            errorStudentDto.setEmail(student.getEmail());
            errorStudentDto.setBirthdate(student.getBirthdate());
            errorStudentDto.setGender(student.getGender());
            errorStudentDto.setEducation(student.getEducation());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid input data", errorStudentDto));
        }

        if (studentService.existsByEmail(student.getEmail())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(student.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Email already in use", errorStudentDto));
        }

        int age = Period.between(student.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setBirthdate(student.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Student must be at least 18 years old", errorStudentDto));
        }

        if (student.getBirthdate().isAfter(LocalDate.now())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setBirthdate(student.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Birthdate cannot be in the future", errorStudentDto));
        }

        if (!regExpEmailValidation(student.getEmail())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(student.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid email format", errorStudentDto));
        }

        if (!regExpPassValidation(student.getPassword())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.addNewProperty("Password", student.getPassword());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Password must have between 8-15 characters, one uppercase letter, one lowercase letter, one number and one special character", errorStudentDto));
        }

        Student newStudent = new Student(student.getFirstName(), student.getLastName(), student.getEmail(), passwordEncoder.encode(student.getPassword()), student.getBirthdate(), LocalDateTime.now(), student.getGender(), student.getRole(), true, student.getEducation());

        StudentDTO newStudentDto = studentService.createStudent(newStudent);

        ApiResponse<StudentDTO> response = new ApiResponse<>("Student created successfully", newStudentDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/students/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<StudentDTO>> updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO studentUpdateDTO, Authentication authentication) {
        if (studentService.getStudentByEmail(authentication.getName()) == null) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorStudentDto));
        }

        Student currentStudent = studentService.getStudentById(id);

        if (currentStudent == null) {
            StudentDTO errorStudentDto = new StudentDTO();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Student not found", errorStudentDto));
        }

        if (!validateStudentFields(studentUpdateDTO)) {
            StudentDTO errorStudentDto = new StudentDTO();

            errorStudentDto.setFirstName(studentUpdateDTO.getFirstName());
            errorStudentDto.setLastName(studentUpdateDTO.getLastName());
            errorStudentDto.setEmail(studentUpdateDTO.getEmail());
            errorStudentDto.setBirthdate(studentUpdateDTO.getBirthdate());
            errorStudentDto.setGender(studentUpdateDTO.getGender());
            errorStudentDto.setEducation(studentUpdateDTO.getEducation());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid input data", errorStudentDto));
        }

        if (!regExpEmailValidation(studentUpdateDTO.getEmail())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(studentUpdateDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid email format", errorStudentDto));
        }

        if (!studentUpdateDTO.getEmail().equals(currentStudent.getEmail()) && studentService.existsByEmail(studentUpdateDTO.getEmail())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(studentUpdateDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Email already in use", errorStudentDto));
        }

        int age = Period.between(studentUpdateDTO.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setBirthdate(studentUpdateDTO.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Student must be at least 18 years old", errorStudentDto));
        }

        if (studentUpdateDTO.getBirthdate().isAfter(LocalDate.now())) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setBirthdate(studentUpdateDTO.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Birthdate cannot be in the future", errorStudentDto));
        }

        currentStudent.setFirstName(studentUpdateDTO.getFirstName());
        currentStudent.setLastName(studentUpdateDTO.getLastName());
        currentStudent.setEmail(studentUpdateDTO.getEmail());
        currentStudent.setBirthdate(studentUpdateDTO.getBirthdate());
        currentStudent.setGender(studentUpdateDTO.getGender());

        StudentDTO uptStudent = studentService.updateStudent(currentStudent);

        return ResponseEntity.ok(new ApiResponse<>("Student updated successfully", uptStudent));
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<ApiResponse<StudentDTO>> deleteStudent(@PathVariable Long id, Authentication authentication) {
        if (studentService.getStudentByEmail(authentication.getName()) == null) {
            StudentDTO errorStudentDto = new StudentDTO();
            errorStudentDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorStudentDto));
        }

        StudentDTO dltStudent = studentService.deleteStudent(id);

        if (dltStudent != null) {
            return ResponseEntity.ok(new ApiResponse<>("Student deleted successfully", dltStudent));
        } else {
            StudentDTO errorStudentDto = new StudentDTO();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Student not found", errorStudentDto));
        }
    }

    @PostMapping("/students/{studentId}/courses/{courseId}/{shift}")
    @Transactional
    public ResponseEntity<ApiResponse<StudentCourseDTO>> enrollCourse(@PathVariable Long studentId, @PathVariable Long courseId, @PathVariable Shift shift, Authentication authentication) {
        if (studentService.getStudentByEmail(authentication.getName()) == null) {
            StudentCourseDTO errorStudentCourseDto = new StudentCourseDTO();
            errorStudentCourseDto.setStudentId(studentService.getStudentByEmail(authentication.getName()).getId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorStudentCourseDto));
        }

        Student student = studentService.getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Student not found", null));
        }

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Course not found", null));
        }

        if (student.getStudentCourses().stream().anyMatch(sc -> sc.getCourse().getId().equals(courseId) && sc.getShift().equals(shift))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Student is already enrolled in this course shift", null));
        }

        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(course);
        studentCourse.setEnrolledDate(LocalDate.now());
        studentCourse.setShift(shift);

        student.addStudentCourse(studentCourse);
        studentService.updateStudent(student);

        return ResponseEntity.ok(new ApiResponse<>("Course enrolled successfully", new StudentCourseDTO(studentCourse)));
    }

    @GetMapping("/students/search")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getStudentsByNameContainAndCourseId(@RequestParam String name, @RequestParam Long courseId, Authentication authentication) {
        if (studentService.getStudentByEmail(authentication.getName()) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", null));
        }
        List<StudentDTO> students = studentService.getStudentsByNameContainAndCourseId(name, courseId);

        return ResponseEntity.ok(new ApiResponse<>("Success", students));
    }
}

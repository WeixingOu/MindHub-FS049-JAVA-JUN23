package com.mindhub.crud.controllers;

import com.mindhub.crud.dtos.*;
import com.mindhub.crud.enums.Shift;
import com.mindhub.crud.models.*;
import com.mindhub.crud.services.CourseService;
import com.mindhub.crud.services.ProfessorService;
import com.mindhub.crud.services.UserService;
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
import java.util.Optional;

import static com.mindhub.crud.utils.ProfessorUtils.validateProfessorFields;
import static com.mindhub.crud.utils.UserUtils.*;

@RestController
@RequestMapping("/api")
public class ProfessorController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/professors")
    public ResponseEntity<ApiResponse<ProfessorDTO>> createProfessor(@RequestBody Professor professor, Authentication authentication) {
        if (professorService.getProfessorByEmail(authentication.getName()) == null) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorProfessorDto));
        }

        if (!validateProfessorFields(professor)) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();

            errorProfessorDto.setFirstName(professor.getFirstName());
            errorProfessorDto.setLastName(professor.getLastName());
            errorProfessorDto.setEmail(professor.getEmail());
            errorProfessorDto.setBirthdate(professor.getBirthdate());
            errorProfessorDto.setGender(professor.getGender());
            errorProfessorDto.setBio(professor.getBio());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid input data", errorProfessorDto));
        }

        if (userService.existsByEmail(professor.getEmail())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(professor.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Email already in use", errorProfessorDto));
        }

        int age = Period.between(professor.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setBirthdate(professor.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Student must be at least 18 years old", errorProfessorDto));
        }

        if (professor.getBirthdate().isAfter(LocalDate.now())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setBirthdate(professor.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Birthdate cannot be in the future", errorProfessorDto));
        }

        if (!regExpEmailValidation(professor.getEmail())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(professor.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid email format", errorProfessorDto));
        }

        if (!regExpPassValidation(professor.getPassword())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.addNewProperty("Password", professor.getPassword());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Password must have between 8-15 characters, one uppercase letter, one lowercase letter, one number and one special character", errorProfessorDto));
        }

        Professor newProfessor = new Professor(professor.getFirstName(), professor.getLastName(), professor.getEmail(), passwordEncoder.encode(professor.getPassword()), professor.getBirthdate(), LocalDateTime.now(), professor.getGender(), professor.getRole(), true, professor.getBio());

        ProfessorDTO newProfessorDto = professorService.createProfessor(newProfessor);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>("Student created successfully", newProfessorDto));
    }

    @PatchMapping("/professors/{id}")
    @Transactional
    public ResponseEntity<ApiResponse<ProfessorDTO>> updateStudent(@PathVariable Long id, @RequestBody ProfessorDTO professorDTO, Authentication authentication) {
        if (professorService.getProfessorByEmail(authentication.getName()) == null) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorProfessorDto));
        }

        Professor currentProfessor = professorService.getProfessorById(id);

        if (currentProfessor == null) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Professor not found", errorProfessorDto));
        }

        if (!validateProfessorFields(professorDTO)) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();

            errorProfessorDto.setFirstName(professorDTO.getFirstName());
            errorProfessorDto.setLastName(professorDTO.getLastName());
            errorProfessorDto.setEmail(professorDTO.getEmail());
            errorProfessorDto.setBirthdate(professorDTO.getBirthdate());
            errorProfessorDto.setGender(professorDTO.getGender());
            errorProfessorDto.setBio(professorDTO.getBio());

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid input data", errorProfessorDto));
        }

        if (!regExpEmailValidation(professorDTO.getEmail())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(professorDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Invalid email format", errorProfessorDto));
        }

        if (!professorDTO.getEmail().equals(currentProfessor.getEmail()) && userService.existsByEmail(professorDTO.getEmail())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(professorDTO.getEmail());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Email already in use", errorProfessorDto));
        }

        int age = Period.between(professorDTO.getBirthdate(), LocalDate.now()).getYears();
        if (age < 18) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setBirthdate(professorDTO.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Student must be at least 18 years old", errorProfessorDto));
        }

        if (professorDTO.getBirthdate().isAfter(LocalDate.now())) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setBirthdate(professorDTO.getBirthdate());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse<>("Birthdate cannot be in the future", errorProfessorDto));
        }

        currentProfessor.setFirstName(professorDTO.getFirstName());
        currentProfessor.setLastName(professorDTO.getLastName());
        currentProfessor.setEmail(professorDTO.getEmail());
        currentProfessor.setBirthdate(professorDTO.getBirthdate());
        currentProfessor.setGender(professorDTO.getGender());

        ProfessorDTO uptProfessor = professorService.updateProfessor(currentProfessor);

        return ResponseEntity.ok(new ApiResponse<>("Student updated successfully", uptProfessor));
    }

    @DeleteMapping("/professor/{id}")
    public ResponseEntity<ApiResponse<ProfessorDTO>> deleteStudent(@PathVariable Long id, Authentication authentication) {
        if (professorService.getProfessorByEmail(authentication.getName()) == null) {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            errorProfessorDto.setEmail(authentication.getName());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorProfessorDto));
        }

        ProfessorDTO dltProfessor = professorService.deleteProfessor(id);

        if (dltProfessor != null) {
            return ResponseEntity.ok(new ApiResponse<>("Student deleted successfully", dltProfessor));
        } else {
            ProfessorDTO errorProfessorDto = new ProfessorDTO();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Student not found", errorProfessorDto));
        }
    }

    @PostMapping("/professors/{professorId}/courses/{courseId}/{shift}")
    @Transactional
    public ResponseEntity<ApiResponse<ProfessorCourseDTO>> teachCourse(@PathVariable Long professorId, @PathVariable Long courseId, @PathVariable Shift shift, Authentication authentication) {
        if (professorService.getProfessorByEmail(authentication.getName()) == null) {
            ProfessorCourseDTO errorProfessorCourseDto = new ProfessorCourseDTO();
            errorProfessorCourseDto.setProfessorId(professorService.getProfessorByEmail(authentication.getName()).getId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorProfessorCourseDto));
        }

        Professor professor = professorService.getProfessorById(professorId);
        Course course = courseService.getCourseById(courseId);

        if (professor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Porfessor not found", null));
        }

        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Course not found", null));
        }

        if (professor.getProfessorCourses().stream().anyMatch(pc -> pc.getCourse().getId().equals(courseId) && pc.getShift().equals(shift))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Professor is already assigned in this course shift", null));
        }

        ProfessorCourse professorCourse = new ProfessorCourse();
        professorCourse.setCourse(course);
        professorCourse.setShift(shift);
        professor.addProfessorCourse(professorCourse);
        professorService.updateProfessor(professor);

        return ResponseEntity.ok(new ApiResponse<>("Course assigned successfully", new ProfessorCourseDTO(professorCourse)));
    }

    @DeleteMapping("/professors/{professorId}/courses/{courseId}/{shift}")
    @Transactional
    public ResponseEntity<ApiResponse<ProfessorCourseDTO>> removeCourseFromProfessor(
            @PathVariable Long professorId,
            @PathVariable Long courseId,
            @PathVariable Shift shift,
            Authentication authentication) {

        if (professorService.getProfessorByEmail(authentication.getName()) == null) {
            ProfessorCourseDTO errorProfessorCourseDto = new ProfessorCourseDTO();
            errorProfessorCourseDto.setProfessorId(professorService.getProfessorByEmail(authentication.getName()).getId());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiResponse<>("Unauthorized user", errorProfessorCourseDto));
        }

        Professor professor = professorService.getProfessorById(professorId);
        if (professor == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Professor not found", null));
        }

        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Course not found", null));
        }

        Optional<ProfessorCourse> professorCourse = professor.getProfessorCourses().stream()
                .filter(pc -> pc.getCourse().getId().equals(courseId) && pc.getShift().equals(shift))
                .findFirst();

        if (!professorCourse.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse<>("Professor is not assigned to this course shift", null));
        }

        professor.removeProfessorCourse(professorCourse.get());
        professorService.updateProfessor(professor);

        ProfessorCourseDTO professorCourseDTO = new ProfessorCourseDTO(professorCourse.get());

        return ResponseEntity.ok(new ApiResponse<>("Professor removed from course shift successfully", professorCourseDTO));
    }
}

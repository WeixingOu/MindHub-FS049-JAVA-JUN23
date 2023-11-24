package com.mindhub.crud.utils;

import com.mindhub.crud.dtos.StudentUpdateDTO;
import com.mindhub.crud.models.Student;

import java.util.regex.Pattern;

public class StudentUtils {
    private StudentUtils() {}

    public static boolean validateStudentFields(Student student) {
        return student.getFirstName() != null && !student.getFirstName().isBlank() &&
                student.getLastName() != null && !student.getLastName().isBlank() &&
                student.getEmail() != null && !student.getEmail().isBlank() &&
                student.getPassword() != null && !student.getPassword().isBlank() &&
                student.getBirthdate() != null &&
                student.getGender() != null &&
                student.getEducation() != null && !student.getEducation().isBlank();
    }

    public static boolean validateStudentFields(StudentUpdateDTO student) {
        return student.getFirstName() != null && !student.getFirstName().isBlank() &&
                student.getLastName() != null && !student.getLastName().isBlank() &&
                student.getEmail() != null && !student.getEmail().isBlank() &&
                student.getBirthdate() != null &&
                student.getGender() != null &&
                student.getEducation() != null && !student.getEducation().isBlank();
    }
}

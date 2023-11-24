package com.mindhub.crud.utils;

import com.mindhub.crud.dtos.ProfessorDTO;
import com.mindhub.crud.models.Professor;

public class ProfessorUtils {
    private ProfessorUtils() {}

    public static boolean validateProfessorFields(Professor professor) {
        return professor.getFirstName() != null && !professor.getFirstName().isBlank() &&
                professor.getLastName() != null && !professor.getLastName().isBlank() &&
                professor.getEmail() != null && !professor.getEmail().isBlank() &&
                professor.getPassword() != null && !professor.getPassword().isBlank() &&
                professor.getBirthdate() != null &&
                professor.getGender() != null &&
                professor.getBio() != null && !professor.getBio().isBlank();
    }

    public static boolean validateProfessorFields(ProfessorDTO professorDTO) {
        return professorDTO.getFirstName() != null && !professorDTO.getFirstName().isBlank() &&
                professorDTO.getLastName() != null && !professorDTO.getLastName().isBlank() &&
                professorDTO.getEmail() != null && !professorDTO.getEmail().isBlank() &&
                professorDTO.getBirthdate() != null &&
                professorDTO.getGender() != null &&
                professorDTO.getBio() != null && !professorDTO.getBio().isBlank();
    }
}

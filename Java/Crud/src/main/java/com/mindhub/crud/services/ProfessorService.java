package com.mindhub.crud.services;

import com.mindhub.crud.dtos.ProfessorDTO;
import com.mindhub.crud.models.Professor;

public interface ProfessorService {
    Professor getProfessorById(Long id);
    Professor getProfessorByEmail(String email);
    ProfessorDTO createProfessor(Professor professor);
    ProfessorDTO updateProfessor(Professor professor);
    ProfessorDTO deleteProfessor(Long id);
}

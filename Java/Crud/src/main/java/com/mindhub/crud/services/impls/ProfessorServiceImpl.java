package com.mindhub.crud.services.impls;

import com.mindhub.crud.dtos.ProfessorDTO;
import com.mindhub.crud.models.Professor;
import com.mindhub.crud.repositories.ProfessorRepository;
import com.mindhub.crud.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorServiceImpl implements ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findByIdAndActiveTrue(id).orElse(null);
    }

    @Override
    public Professor getProfessorByEmail(String email) {
        return professorRepository.findByEmailAndActiveTrue(email).orElse(null);
    }

    @Override
    public ProfessorDTO createProfessor(Professor professor) {
        Professor professorUpt = professorRepository.save(professor);
        return new ProfessorDTO(professorUpt);
    }

    @Override
    public ProfessorDTO updateProfessor(Professor professor) {
        Professor professorUpt = professorRepository.save(professor);
        return new ProfessorDTO(professorUpt);
    }

    @Override
    public ProfessorDTO deleteProfessor(Long id) {
        Optional<Professor> professorOpt = professorRepository.findById(id);
        if (professorOpt.isPresent()) {
            Professor professor = professorOpt.get();

            professor.setActive(false);
            Professor professorDlt = professorRepository.save(professor);

            return new ProfessorDTO(professorDlt);
        }
        return null;
    }
}

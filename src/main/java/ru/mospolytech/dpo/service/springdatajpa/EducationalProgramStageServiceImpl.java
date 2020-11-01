package ru.mospolytech.dpo.service.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.EducationalProgramStage;
import ru.mospolytech.dpo.repository.EducationalProgramStageRepository;
import ru.mospolytech.dpo.service.EducationalProgramStageService;

@Service
public class EducationalProgramStageServiceImpl implements EducationalProgramStageService {
    
    private final EducationalProgramStageRepository educationalProgramStageRepository;
    
    public EducationalProgramStageServiceImpl(EducationalProgramStageRepository educationalProgramStageRepository) {
        this.educationalProgramStageRepository = educationalProgramStageRepository;
    }

    @Override
    public Set<EducationalProgramStage> findAll() {
        Set<EducationalProgramStage> educationalProgramStages = new HashSet<>();
        educationalProgramStageRepository.findAll().forEach(educationalProgramStages::add);
        return educationalProgramStages;
    }

    @Override
    public void deleteById(Long id) {
        educationalProgramStageRepository.deleteById(id);
    }

    @Override
    public EducationalProgramStage save(EducationalProgramStage object) {
        return educationalProgramStageRepository.save(object);
    }

    @Override
    public void delete(EducationalProgramStage object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EducationalProgramStage findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<EducationalProgramStage> findByCourseId(Long id) {
        return educationalProgramStageRepository.findByCourseId(id);
    }
    
}

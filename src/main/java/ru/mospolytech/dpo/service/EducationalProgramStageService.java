package ru.mospolytech.dpo.service;

import java.util.Optional;
import java.util.Set;
import ru.mospolytech.dpo.domain.EducationalProgramStage;

public interface EducationalProgramStageService extends CrudService<EducationalProgramStage, Long> {
    Set<EducationalProgramStage> findAllByCourseId(Long id);
}

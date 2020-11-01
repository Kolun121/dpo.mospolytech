package ru.mospolytech.dpo.service;

import java.util.Optional;
import ru.mospolytech.dpo.domain.EducationalProgramStage;

public interface EducationalProgramStageService extends CrudService<EducationalProgramStage, Long> {
    Optional<EducationalProgramStage> findByCourseId(Long id);
}

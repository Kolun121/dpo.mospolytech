package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.EducationalProgramStage;


public interface EducationalProgramStageRepository extends CrudRepository<EducationalProgramStage, Long>{
    Optional<EducationalProgramStage> findByCourseId(Long id);
}

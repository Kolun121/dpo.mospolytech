package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.Teacher;


public interface TeacherRepository extends CrudRepository<Teacher, Long>{
    Optional<Teacher> findById(Long id);
}

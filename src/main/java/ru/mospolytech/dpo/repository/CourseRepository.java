package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.Course;


public interface CourseRepository extends CrudRepository<Course, Long>{
    Optional<Course> findById(Long id);
    Optional<Course> findByUrlSegment(String urlSegment);
}

package ru.mospolytech.dpo.service;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.EntityId;

public interface CourseService extends CrudService<Course, Long> {
    void deleteByIdAndVersion(Long id, Long version);
    Course findByIdAndVersion(Long id, Long version);
    Course findByUrlSegment(String urlSegment);
    List<Course> findAllByIdByOrderByCreatedAtAsc(Long id);
    Page<Course> findAllPageableSpec(Specification<Course> filter, Pageable pageable);
    Integer min();
    Integer max();
    Long maxId();
    Long maxVersionWhereId(Long id);
}

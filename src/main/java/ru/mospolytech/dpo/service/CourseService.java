package ru.mospolytech.dpo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.mospolytech.dpo.domain.Course;

public interface CourseService extends CrudService<Course, Long> {
    Course findByUrlSegment(String urlSegment);
    Page<Course> findAllPageableSpec(Specification<Course> filter, Pageable pageable);
    Integer min();
    Integer max();
}

package ru.mospolytech.dpo.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.Course;


public interface CourseRepository extends CrudRepository<Course, Long>, JpaSpecificationExecutor<Course> {
    Optional<Course> findById(Long id);
    Optional<Course> findByUrlSegment(String urlSegment);
//    Page<Course> findAll(Pageable pageable);

    Page<Course> findAll(Specification<Course> filter, Pageable pageable);
    
    @Query(value = "SELECT min(coursePrice) FROM Course")
    public Integer min();
    
    @Query(value = "SELECT max(coursePrice) FROM Course")
    public Integer max();
}

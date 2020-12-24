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
import ru.mospolytech.dpo.domain.EntityId;


public interface CourseRepository extends CrudRepository<Course, EntityId>, JpaSpecificationExecutor<Course> {
    Optional<Course> findById(EntityId ei);
//    Set<Course> findAllByVersion(Long version);
    Optional<Course> findByUrlSegment(String urlSegment);
    List<Course> findAllByIdOrderByUpdatedAtDesc(Long id);
    @Query(value = "SELECT min(coursePrice) FROM Course")
    public Integer min();
    
    @Query(value = "SELECT max(coursePrice) FROM Course")
    public Integer max();
    
    @Query(value = "SELECT max(id) FROM Course")
    Optional<Long> maxId();
    
//    @Query(value = "SELECT max(version) FROM Course")
//    Optional<Long> maxId();
    @Query("select max(c.version) from Course c where  c.id = ?1")
    Optional<Long> maxVersionWhereId(Long id);
}

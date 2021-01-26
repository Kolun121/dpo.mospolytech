package ru.mospolytech.dpo.service.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.EntityId;
import ru.mospolytech.dpo.repository.CourseRepository;
import ru.mospolytech.dpo.service.CourseService;

//@Transactional
@Service
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Set<Course> findAll() {
        Set<Course> courses = new HashSet<>();
        courseRepository.findAll().forEach(courses::add);
        
        Set<Course> coursesFilteredByVersion = courses.stream().filter((Course c)-> c.getVersion().equals(0l)).collect(Collectors.toSet());
        return coursesFilteredByVersion;
    }

    @Override
    public Course findById(Long id) {
        EntityId entityId = new EntityId(id, 0l);

        Optional<Course> courseOptional = courseRepository.findById(entityId);
        
        if(!courseOptional.isPresent()){
            throw new RuntimeException("Курс не найден");
        }
        
        return courseOptional.get();
    }

    @Override
    public Course save(Course object) {
        return courseRepository.save(object);
    }

    @Override
    public void delete(Course object) {
        courseRepository.delete(object);
    }

    
    @Override
    public void deleteByIdAndVersion(Long id, Long version) {
        EntityId entityId = new EntityId(id, version);
        courseRepository.deleteById(entityId);
    }

    @Override
    public Course findByUrlSegment(String urlSegment) {
        Optional<Course> courseOptional = courseRepository.findByUrlSegment(urlSegment);
        
        if(!courseOptional.isPresent()){
            throw new RuntimeException("Курс не найден");
        }
        
        return courseOptional.get();
    }

    @Override
    public Integer min() {
        return courseRepository.min();
    }

    @Override
    public Integer max() {
        return courseRepository.max();
    }

    @Override
    public Page<Course> findAllPageableSpec(Specification<Course> filter, Pageable pageable) {
        return courseRepository.findAll(filter, pageable);
    }

    @Override
    public Long maxId() {
        Optional<Long> maxId = courseRepository.maxId();
        
        if(!maxId.isPresent()){
            return 0l;
        }
        
        return maxId.get();
    }

    @Override
    public Long maxVersionWhereId(Long id) {
        Optional<Long> maxVersion = courseRepository.maxVersionWhereId(id);
        
        if(!maxVersion.isPresent()){
            throw new RuntimeException("Ошибка!");
        }
        
        return maxVersion.get();
    }

    @Override
    public void deleteById(Long id) {
        EntityId entityId = new EntityId(id, 0l);
        courseRepository.deleteById(entityId);
    }

    @Override
    public List<Course> findAllByIdByOrderByCreatedAtAsc(Long id) {
        return courseRepository.findAllByIdOrderByUpdatedAtDesc(id);
    }

    @Override
    public Course findByIdAndVersion(Long id, Long version) {
        EntityId entityId = new EntityId(id, version);

        Optional<Course> courseOptional = courseRepository.findById(entityId);
        
        if(!courseOptional.isPresent()){
            throw new RuntimeException("Курс не найден");
        }
        
        return courseOptional.get();
    }
    
}

package ru.mospolytech.dpo.service.springdatajpa;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.repository.CourseRepository;
import ru.mospolytech.dpo.service.CourseService;

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
        return courses;
    }

    @Override
    public Course findById(Long id) {
        Optional<Course> courseOptional = courseRepository.findById(id);
        
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
    
}

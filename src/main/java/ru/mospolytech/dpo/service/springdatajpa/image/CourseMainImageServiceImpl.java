package ru.mospolytech.dpo.service.springdatajpa.image;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.image.CourseMainImage;
import ru.mospolytech.dpo.repository.image.CourseMainImageRepository;
import ru.mospolytech.dpo.service.image.CourseMainImageService;

@Service
public class CourseMainImageServiceImpl implements CourseMainImageService {
    
    private final CourseMainImageRepository courseMainImageRepository;
    
    public CourseMainImageServiceImpl(CourseMainImageRepository courseMainImageRepository) {
        this.courseMainImageRepository = courseMainImageRepository;
    }

    @Override
    public CourseMainImage save(CourseMainImage courseMainImage) {
        return courseMainImageRepository.save(courseMainImage);
    }

    @Override
    public void delete(CourseMainImage object) {
        courseMainImageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        courseMainImageRepository.deleteById(id);
    }

    @Override
    public CourseMainImage findByCourseId(Long courseId) {
        Optional<CourseMainImage> courseMainImageOptional = courseMainImageRepository.findByCourseId(courseId);
        
        if (!courseMainImageOptional.isPresent()) {
            throw new RuntimeException("Изображение не найдено по ID курса:" + courseId );
        }

        return courseMainImageOptional.get();
    }
    
}

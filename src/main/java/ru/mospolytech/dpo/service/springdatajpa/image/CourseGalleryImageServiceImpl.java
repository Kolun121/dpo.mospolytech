package ru.mospolytech.dpo.service.springdatajpa.image;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.image.CourseGalleryImage;
import ru.mospolytech.dpo.repository.image.CourseGalleryImageRepository;
import ru.mospolytech.dpo.service.image.CourseGalleryImageService;

@Service
public class CourseGalleryImageServiceImpl implements CourseGalleryImageService {
    
    private final CourseGalleryImageRepository courseGalleryImageRepository;
    
    public CourseGalleryImageServiceImpl(CourseGalleryImageRepository courseGalleryImageRepository) {
        this.courseGalleryImageRepository = courseGalleryImageRepository;
    }

    @Override
    public CourseGalleryImage save(CourseGalleryImage object) {
        return courseGalleryImageRepository.save(object);
    }

    @Override
    public void delete(CourseGalleryImage object) {
        courseGalleryImageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        courseGalleryImageRepository.deleteById(id);
    }

    @Override
    public CourseGalleryImage findById(Long Id) {
        
        Optional<CourseGalleryImage> courseGalleryImageOptional = courseGalleryImageRepository.findById(Id);
        
        if (!courseGalleryImageOptional.isPresent()) {
            throw new RuntimeException("Изображение не найдено по ID:" + Id);
        }

        return courseGalleryImageOptional.get();
    }

    
}

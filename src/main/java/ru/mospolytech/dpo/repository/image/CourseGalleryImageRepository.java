package ru.mospolytech.dpo.repository.image;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.image.CourseGalleryImage;


public interface CourseGalleryImageRepository extends CrudRepository<CourseGalleryImage, Long>{
//    Optional<CourseGalleryImage> findByCourseId(Long courseId);
}

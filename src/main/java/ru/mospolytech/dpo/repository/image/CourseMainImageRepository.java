package ru.mospolytech.dpo.repository.image;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.image.CourseMainImage;


public interface CourseMainImageRepository extends CrudRepository<CourseMainImage, Long>{
    Optional<CourseMainImage> findByCourseId(Long courseId);
}

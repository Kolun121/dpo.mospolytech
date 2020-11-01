package ru.mospolytech.dpo.repository.image;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.image.TeacherMainImage;


public interface TeacherMainImageRepository extends CrudRepository<TeacherMainImage, Long>{
    Optional<TeacherMainImage> findByTeacherId(Long teacherId);
}

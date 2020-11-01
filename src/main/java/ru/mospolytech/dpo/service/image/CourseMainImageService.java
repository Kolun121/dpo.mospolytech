package ru.mospolytech.dpo.service.image;

import ru.mospolytech.dpo.domain.image.CourseMainImage;

public interface CourseMainImageService extends BaseImageService<CourseMainImage, Long> {
    CourseMainImage findByCourseId(Long courseId);
}
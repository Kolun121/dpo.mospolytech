package ru.mospolytech.dpo.service.image;

import ru.mospolytech.dpo.domain.image.CourseGalleryImage;

public interface CourseGalleryImageService extends BaseImageService<CourseGalleryImage, Long> {
    CourseGalleryImage findById(Long Id);
}
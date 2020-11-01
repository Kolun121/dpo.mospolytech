package ru.mospolytech.dpo.service.image;

import ru.mospolytech.dpo.domain.image.TeacherMainImage;

public interface TeacherMainImageService extends BaseImageService<TeacherMainImage, Long> {
    TeacherMainImage findByTeacherId(Long teacherId);
}
package ru.mospolytech.dpo.service.image;

import ru.mospolytech.dpo.domain.image.NewsMainImage;

public interface NewsMainImageService extends BaseImageService<NewsMainImage, Long> {
    NewsMainImage findByNewsId(Long newsId);
}
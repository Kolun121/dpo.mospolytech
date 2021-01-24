package ru.mospolytech.dpo.service.image;

import ru.mospolytech.dpo.domain.image.IndexItemImage;

public interface IndexItemImageService extends BaseImageService<IndexItemImage, Long> {
    IndexItemImage findByIndexItemId(Long indexItemId);
}
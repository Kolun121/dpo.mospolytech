package ru.mospolytech.dpo.service.image;

import ru.mospolytech.dpo.domain.image.ContactMainImage;

public interface ContactMainImageService extends BaseImageService<ContactMainImage, Long> {
    ContactMainImage findByContactId(Long contactId);
}
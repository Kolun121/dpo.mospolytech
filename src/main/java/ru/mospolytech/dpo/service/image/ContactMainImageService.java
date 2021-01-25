package ru.mospolytech.dpo.service.image;

import org.springframework.web.multipart.MultipartFile;
import ru.mospolytech.dpo.domain.image.ContactMainImage;

public interface ContactMainImageService extends BaseImageService<ContactMainImage, Long> {
    ContactMainImage findByContactId(Long contactId);
}
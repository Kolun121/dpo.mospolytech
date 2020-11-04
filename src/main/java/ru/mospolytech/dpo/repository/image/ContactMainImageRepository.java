package ru.mospolytech.dpo.repository.image;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.image.ContactMainImage;


public interface ContactMainImageRepository extends CrudRepository<ContactMainImage, Long>{
    Optional<ContactMainImage> findByContactId(Long contactId);
}

package ru.mospolytech.dpo.repository.image;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.image.IndexItemImage;


public interface IndexItemImageRepository extends CrudRepository<IndexItemImage, Long>{
    Optional<IndexItemImage> findByIndexItemId(Long indexItemId);
}

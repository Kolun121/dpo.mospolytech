package ru.mospolytech.dpo.repository.image;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.image.NewsMainImage;


public interface NewsMainImageRepository extends CrudRepository<NewsMainImage, Long>{
    Optional<NewsMainImage> findByNewsId(Long newsId);
}

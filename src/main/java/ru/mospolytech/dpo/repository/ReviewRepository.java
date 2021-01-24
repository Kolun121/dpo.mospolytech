package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.Review;


public interface ReviewRepository extends CrudRepository<Review, Long>{
    Optional<Review> findById(Long id);
}

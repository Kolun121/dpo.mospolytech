package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.News;


public interface NewsRepository extends CrudRepository<News, Long>{
    Optional<News> findById(Long id);
}

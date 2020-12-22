package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.News;


public interface NewsRepository extends CrudRepository<News, Long>, JpaSpecificationExecutor<News>{
    Optional<News> findById(Long id);
    Page<News> findAll(Specification<News> filter, Pageable pageable);
    Optional<News> findByUrlSegment(String urlSegment);

}

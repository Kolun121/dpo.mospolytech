package ru.mospolytech.dpo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import ru.mospolytech.dpo.domain.News;

public interface NewsService extends CrudService<News, Long> {
    Page<News> findAllPageableSpec(Specification<News> filter, Pageable pageable);
    News findByUrlSegment(String urlSegment);

}

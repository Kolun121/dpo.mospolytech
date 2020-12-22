package ru.mospolytech.dpo.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ru.mospolytech.dpo.domain.News;
import ru.mospolytech.dpo.domain.News_;

import ru.mospolytech.dpo.domain.enumeration.Status;

public class NewsSpecification {
 
    
    public static Specification<News> hasPublishedStatus() {
        return (Root<News> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<Status> newsStatus = root.get(News_.newsStatus);
            Predicate equalPredicate = builder.equal(newsStatus, Status.PUBLISHED);
            return equalPredicate;   
        };
    }

}

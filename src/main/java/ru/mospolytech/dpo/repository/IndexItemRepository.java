package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.IndexItem;


public interface IndexItemRepository extends CrudRepository<IndexItem, Long>{
    Optional<IndexItem> findById(Long id);
}

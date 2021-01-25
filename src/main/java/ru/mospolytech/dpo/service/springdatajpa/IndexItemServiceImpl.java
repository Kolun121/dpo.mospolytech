package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.IndexItem;
import ru.mospolytech.dpo.repository.IndexItemRepository;
import ru.mospolytech.dpo.service.IndexItemService;

@Service
public class IndexItemServiceImpl implements IndexItemService {
    
    private final IndexItemRepository indexItemRepository;
    
    public IndexItemServiceImpl(IndexItemRepository indexItemRepository) {
        this.indexItemRepository = indexItemRepository;
    }

    @Override
    public Set<IndexItem> findAll() {
        Set<IndexItem> indexItems = new HashSet<>();
        indexItemRepository.findAll().forEach(indexItems::add);
        return indexItems;
    }

    @Override
    public IndexItem findById(Long id) {
        Optional<IndexItem> indexItemOptional = indexItemRepository.findById(id);
        
        if(!indexItemOptional.isPresent()){
            throw new RuntimeException("Index Item не найден");
        }
        
        return indexItemOptional.get();
    }

    @Override
    public IndexItem save(IndexItem object) {
        System.out.println(object.getText());
        return indexItemRepository.save(object);
    }

    @Override
    public void delete(IndexItem object) {
        indexItemRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        indexItemRepository.deleteById(id);
    }

    
}

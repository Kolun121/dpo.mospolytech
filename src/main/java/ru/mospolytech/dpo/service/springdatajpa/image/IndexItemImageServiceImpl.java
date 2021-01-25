package ru.mospolytech.dpo.service.springdatajpa.image;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.image.IndexItemImage;
import ru.mospolytech.dpo.repository.image.IndexItemImageRepository;
import ru.mospolytech.dpo.service.image.IndexItemImageService;

@Service
public class IndexItemImageServiceImpl implements IndexItemImageService {
    
    private final IndexItemImageRepository indexItemImageRepository;
    
    public IndexItemImageServiceImpl(IndexItemImageRepository indexItemImageRepository) {
        this.indexItemImageRepository = indexItemImageRepository;
    }

    @Override
    public IndexItemImage save(IndexItemImage courseMainImage) {
        return indexItemImageRepository.save(courseMainImage);
    }

    @Override
    public void delete(IndexItemImage object) {
        indexItemImageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        indexItemImageRepository.deleteById(id);
    }

    @Override
    public IndexItemImage findByIndexItemId(Long indexItemId) {
        Optional<IndexItemImage> indexImageOptional = indexItemImageRepository.findByIndexItemId(indexItemId);
        
        if (!indexImageOptional.isPresent()) {
            return null;
        }

        return indexImageOptional.get();
    }

    @Override
    public IndexItemImage findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

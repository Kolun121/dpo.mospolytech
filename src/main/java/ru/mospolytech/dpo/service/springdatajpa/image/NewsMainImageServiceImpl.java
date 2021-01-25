package ru.mospolytech.dpo.service.springdatajpa.image;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.image.NewsMainImage;
import ru.mospolytech.dpo.repository.image.NewsMainImageRepository;
import ru.mospolytech.dpo.service.image.NewsMainImageService;

@Service
public class NewsMainImageServiceImpl implements NewsMainImageService {
    
    private final NewsMainImageRepository newsMainImageRepository;
    
    public NewsMainImageServiceImpl(NewsMainImageRepository newsMainImageRepository) {
        this.newsMainImageRepository = newsMainImageRepository;
    }

    @Override
    public NewsMainImage findByNewsId(Long newsId) {
        Optional<NewsMainImage> newsMainImageOptional = newsMainImageRepository.findByNewsId(newsId);
        
        if (!newsMainImageOptional.isPresent()) {
            throw new RuntimeException("Изображение не найдено по ID новости:" + newsId );
        }

        return newsMainImageOptional.get();
    }

    @Override
    public NewsMainImage save(NewsMainImage object) {
        return newsMainImageRepository.save(object);
    }

    @Override
    public void delete(NewsMainImage object) {
        newsMainImageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        newsMainImageRepository.deleteById(id);
    }

    @Override
    public NewsMainImage findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

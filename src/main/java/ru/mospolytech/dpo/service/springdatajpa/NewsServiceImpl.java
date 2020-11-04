package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.News;
import ru.mospolytech.dpo.repository.NewsRepository;
import ru.mospolytech.dpo.service.NewsService;

@Service
public class NewsServiceImpl implements NewsService {
    
    private final NewsRepository newsRepository;
    
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public Set<News> findAll() {
        Set<News> news = new HashSet<>();
        newsRepository.findAll().forEach(news::add);
        return news;
    }

    @Override
    public News findById(Long id) {
        Optional<News> newsOptional = newsRepository.findById(id);
        
        if(!newsOptional.isPresent()){
            throw new RuntimeException("Новость не найдена");
        }
        
        return newsOptional.get();
    }

    @Override
    public News save(News object) {
        return newsRepository.save(object);
    }

    @Override
    public void delete(News object) {
        newsRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }

    
}

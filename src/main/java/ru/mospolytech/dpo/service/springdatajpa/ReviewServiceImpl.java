package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.Review;
import ru.mospolytech.dpo.repository.ReviewRepository;
import ru.mospolytech.dpo.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewRepository reviewRepository;
    
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Set<Review> findAll() {
        Set<Review> reviews = new HashSet<>();
        reviewRepository.findAll().forEach(reviews::add);
        return reviews;
    }

    @Override
    public Review findById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        
        if(!reviewOptional.isPresent()){
            throw new RuntimeException("Review не найден");
        }
        
        return reviewOptional.get();
    }

    @Override
    public Review save(Review object) {
        return reviewRepository.save(object);
    }

    @Override
    public void delete(Review object) {
        reviewRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        reviewRepository.deleteById(id);
    }

    
}

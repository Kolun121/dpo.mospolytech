package ru.mospolytech.dpo.service.springdatajpa.image;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.image.ContactMainImage;
import ru.mospolytech.dpo.repository.image.ContactMainImageRepository;
import ru.mospolytech.dpo.service.image.ContactMainImageService;



@Service
public class ContactMainImageServiceImpl implements ContactMainImageService {
    
    private final ContactMainImageRepository сontactMainImageRepository;

    public ContactMainImageServiceImpl(ContactMainImageRepository сontactMainImageRepository) {
        this.сontactMainImageRepository = сontactMainImageRepository;
    }
    

    @Override
    public ContactMainImage findByContactId(Long contactId) {
        Optional<ContactMainImage> сontactMainImageOptional = сontactMainImageRepository.findByContactId(contactId);
        
        if (!сontactMainImageOptional.isPresent()) {
            return null;
        }

        return сontactMainImageOptional.get();
    }

    @Override
    public ContactMainImage save(ContactMainImage object) {
        return сontactMainImageRepository.save(object);
    }

    @Override
    public void delete(ContactMainImage object) {
        сontactMainImageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        сontactMainImageRepository.deleteById(id);
    }

    @Override
    public ContactMainImage findById(Long id) {
        return сontactMainImageRepository.findById(id).orElse(new ContactMainImage());
    }
    
}

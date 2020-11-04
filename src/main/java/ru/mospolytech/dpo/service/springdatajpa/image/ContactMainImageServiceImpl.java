package ru.mospolytech.dpo.service.springdatajpa.image;

import java.util.Optional;
import org.springframework.stereotype.Service;
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
            throw new RuntimeException("Изображение не найдено по ID контакта:" + contactId );
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
    
}

package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.Contact;
import ru.mospolytech.dpo.repository.ContactRepository;
import ru.mospolytech.dpo.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    
    private final ContactRepository contactRepository;
    
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Set<Contact> findAll() {
        Set<Contact> contacts = new HashSet<>();
        contactRepository.findAll().forEach(contacts::add);
        return contacts;
    }

    @Override
    public Contact findById(Long id) {
        Optional<Contact> contactOptional = contactRepository.findById(id);
        
        if(!contactOptional.isPresent()){
            throw new RuntimeException("Контакт не найден");
        }
        
        return contactOptional.get();
    }

    @Override
    public Contact save(Contact object) {
        return contactRepository.save(object);
    }

    @Override
    public void delete(Contact object) {
        contactRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }

    
}

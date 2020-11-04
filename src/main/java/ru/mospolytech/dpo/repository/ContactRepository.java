package ru.mospolytech.dpo.repository;

import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.Contact;


public interface ContactRepository extends CrudRepository<Contact, Long>{
}

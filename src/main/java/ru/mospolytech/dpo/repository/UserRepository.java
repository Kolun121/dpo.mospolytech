package ru.mospolytech.dpo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.User;


public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByUsername(String username);
}

package ru.mospolytech.dpo.repository;


import org.springframework.data.repository.CrudRepository;
import ru.mospolytech.dpo.domain.UserFeedBack;


public interface UserFeedBackRepository extends CrudRepository<UserFeedBack, Long>{

}

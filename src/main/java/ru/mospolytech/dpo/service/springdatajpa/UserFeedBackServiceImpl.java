package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.UserFeedBack;
import ru.mospolytech.dpo.repository.UserFeedBackRepository;
import ru.mospolytech.dpo.service.UserFeedBackService;

@Service
public class UserFeedBackServiceImpl implements UserFeedBackService {
    
    private final UserFeedBackRepository userFeedBackRepository;
    
    public UserFeedBackServiceImpl(UserFeedBackRepository userFeedBackRepository) {
        this.userFeedBackRepository = userFeedBackRepository;
    }

    @Override
    public Set<UserFeedBack> findAll() {
        Set<UserFeedBack> userFeedBacks = new HashSet<>();
        userFeedBackRepository.findAll().forEach(userFeedBacks::add);
        return userFeedBacks;
    }

    @Override
    public UserFeedBack findById(Long id) {
        Optional<UserFeedBack> userFeedBackOptional = userFeedBackRepository.findById(id);
        
        if(!userFeedBackOptional.isPresent()){
            throw new RuntimeException("Заявка не найдена");
        }
        
        return userFeedBackOptional.get();
    }

    @Override
    public UserFeedBack save(UserFeedBack object) {
        return userFeedBackRepository.save(object);
    }

    @Override
    public void delete(UserFeedBack object) {
        userFeedBackRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        userFeedBackRepository.deleteById(id);
    }

    
}

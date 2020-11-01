package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.Teacher;
import ru.mospolytech.dpo.repository.TeacherRepository;
import ru.mospolytech.dpo.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
    
    private final TeacherRepository teacherRepository;
    
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Set<Teacher> findAll() {
        Set<Teacher> teachers = new HashSet<>();
        teacherRepository.findAll().forEach(teachers::add);
        return teachers;
    }

    @Override
    public Teacher findById(Long id) {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);
        
        if(!teacherOptional.isPresent()){
            throw new RuntimeException("Преподаватель не найден");
        }
        
        return teacherOptional.get();
    }

    @Override
    public Teacher save(Teacher object) {
        return teacherRepository.save(object);
    }

    @Override
    public void delete(Teacher object) {
        teacherRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    
}

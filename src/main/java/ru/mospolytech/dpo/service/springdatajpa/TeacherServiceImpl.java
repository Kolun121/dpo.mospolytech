package ru.mospolytech.dpo.service.springdatajpa;

import java.util.Set;
import java.util.HashSet;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Teacher save(Teacher object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Teacher object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

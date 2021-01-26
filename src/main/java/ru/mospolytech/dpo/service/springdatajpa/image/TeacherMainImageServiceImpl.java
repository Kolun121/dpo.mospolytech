package ru.mospolytech.dpo.service.springdatajpa.image;

import java.util.Optional;
import org.springframework.stereotype.Service;
import ru.mospolytech.dpo.domain.image.TeacherMainImage;
import ru.mospolytech.dpo.repository.image.TeacherMainImageRepository;
import ru.mospolytech.dpo.service.image.TeacherMainImageService;

@Service
public class TeacherMainImageServiceImpl implements TeacherMainImageService {
    
    private final TeacherMainImageRepository teacherMainImageRepository;
    
    public TeacherMainImageServiceImpl(TeacherMainImageRepository teacherMainImageRepository) {
        this.teacherMainImageRepository = teacherMainImageRepository;
    }

    @Override
    public TeacherMainImage findByTeacherId(Long teacherId) {
        Optional<TeacherMainImage> teacherMainImageOptional = teacherMainImageRepository.findByTeacherId(teacherId);
        
        if (!teacherMainImageOptional.isPresent()) {
            return null;
        }

        return teacherMainImageOptional.get();
    }

    @Override
    public TeacherMainImage save(TeacherMainImage object) {
        return teacherMainImageRepository.save(object);
    }

    @Override
    public void delete(TeacherMainImage object) {
        teacherMainImageRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        teacherMainImageRepository.deleteById(id);
    }

    @Override
    public TeacherMainImage findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

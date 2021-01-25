package ru.mospolytech.dpo.controllers.admin.image;

import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.Teacher;
import ru.mospolytech.dpo.domain.image.TeacherMainImage;
import ru.mospolytech.dpo.service.TeacherService;
import ru.mospolytech.dpo.service.image.TeacherMainImageService;

@Slf4j
@Controller("adminTeacherMainImageController")
public class TeacherMainImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "teachers";
   
    private final TeacherMainImageService teacherMainImageService;
    private final TeacherService teacherService;
    private final AmazonClient amazonClient;
    
    public TeacherMainImageController(TeacherMainImageService teacherMainImageService, TeacherService teacherService, AmazonClient amazonClient) {
        this.teacherMainImageService = teacherMainImageService;
        this.teacherService = teacherService;
        this.amazonClient = amazonClient;
    }

    
    @PostMapping("/admin/teachers/{teacherId}/image/new")
    public @ResponseBody String addMainTeacherImage(
            @PathVariable Long teacherId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = amazonClient.uploadFile(file, imageControllerDir);
        
        Teacher teacher = teacherService.findById(teacherId);

        TeacherMainImage teacherMainImage = new TeacherMainImage();
        teacherMainImage.setName(url);
        teacherMainImage.setTeacher(teacher);
        teacher.setMainImage(teacherMainImage);

        teacherMainImageService.save(teacherMainImage);
        
        
        return url;
    }
    
    @DeleteMapping("/admin/teachers/{teacherId}/image")
    public @ResponseBody void deleteMainTeacherImage(
            @PathVariable Long teacherId
    ) throws IOException {
        Teacher teacher = teacherService.findById(teacherId);
        TeacherMainImage teacherMainImage = teacherMainImageService.findByTeacherId(teacherId);
        teacher.setMainImage(null);
        
        amazonClient.deleteFileFromS3Bucket(teacherMainImage.getName(), imageControllerDir);
        
        teacherMainImageService.delete(teacherMainImage);
    }
}

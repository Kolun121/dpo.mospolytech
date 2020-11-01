package ru.mospolytech.dpo.controllers.admin.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.mospolytech.dpo.domain.Teacher;
import ru.mospolytech.dpo.domain.image.TeacherMainImage;
import ru.mospolytech.dpo.service.TeacherService;
import ru.mospolytech.dpo.service.image.TeacherMainImageService;

@Slf4j
@Controller("adminTeacherMainImageController")
public class TeacherMainImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "/teachers";
   
    private final TeacherMainImageService teacherMainImageService;
    private final TeacherService teacherService;

    public TeacherMainImageController(TeacherMainImageService teacherMainImageService, TeacherService teacherService) {
        this.teacherMainImageService = teacherMainImageService;
        this.teacherService = teacherService;
    }

    
    @PostMapping("/admin/teachers/{teacherId}/image/new")
    public @ResponseBody String addMainTeacherImage(
            @PathVariable Long teacherId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        
        String resultFilename = "none";
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath + imageControllerDir);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String uuidFile  = UUID.randomUUID().toString();
            resultFilename = uuidFile + "." + "png";

            BufferedImage image = ImageIO.read(file.getInputStream());

            File outputFile = new File(uploadPath + imageControllerDir + "/" + resultFilename);
            ImageIO.write(image, "png", outputFile);  
            
            Teacher teacher = teacherService.findById(teacherId);
            
            TeacherMainImage teacherMainImage = new TeacherMainImage();
            teacherMainImage.setName(resultFilename);
            teacherMainImage.setTeacher(teacher);
            teacher.setMainImage(teacherMainImage);
            
            teacherMainImageService.save(teacherMainImage);
        }
        
        return resultFilename;
    }
    
    @DeleteMapping("/admin/teachers/{teacherId}/image")
    public @ResponseBody void deleteMainTeacherImage(
            @PathVariable Long teacherId
    ) throws IOException {
        Teacher teacher = teacherService.findById(teacherId);
        TeacherMainImage teacherMainImage = teacherMainImageService.findByTeacherId(teacherId);
        teacher.setMainImage(null);
        
        Path imageToDeletePath = Paths.get(uploadPath + imageControllerDir + "/" + teacherMainImage.getName());
        Files.delete(imageToDeletePath);
        
        teacherMainImageService.delete(teacherMainImage);
    }
}

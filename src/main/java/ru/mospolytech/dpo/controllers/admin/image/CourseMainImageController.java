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
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.image.CourseMainImage;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.image.CourseMainImageService;

@Slf4j
@Controller("adminCourseMainImageController")
public class CourseMainImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "courses/main_image";
   
    private final CourseMainImageService courseMainImageService;
    private final CourseService courseService;
    private final AmazonClient amazonClient;

    public CourseMainImageController(CourseMainImageService courseMainImageService, CourseService courseService, AmazonClient amazonClient) {
        this.courseMainImageService = courseMainImageService;
        this.courseService = courseService;
        this.amazonClient = amazonClient;
    }

    
    @PostMapping("/admin/courses/{courseId}/image/new")
    public @ResponseBody String addMainCourseImage(
            @PathVariable Long courseId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        String url = amazonClient.uploadFile(file, imageControllerDir);
        
        Course course = courseService.findById(courseId);

        CourseMainImage courseMainImage = new CourseMainImage();
        courseMainImage.setName(url);
        courseMainImage.setCourse(course);
        course.setMainImage(courseMainImage);
        courseMainImageService.save(courseMainImage);
        
        
        return url;
    }
    
    @DeleteMapping("/admin/courses/{courseId}/image")
    public @ResponseBody void deleteMainCourseImage(
            @PathVariable Long courseId
    ) throws IOException {
        Course course = courseService.findById(courseId);
        CourseMainImage courseMainImage = courseMainImageService.findByCourseId(courseId);
        course.setMainImage(null);
        
        amazonClient.deleteFileFromS3Bucket(courseMainImage.getName(), imageControllerDir);
        
        courseMainImageService.delete(courseMainImage);
    }
}

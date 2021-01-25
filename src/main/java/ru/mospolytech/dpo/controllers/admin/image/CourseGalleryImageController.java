package ru.mospolytech.dpo.controllers.admin.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import javax.imageio.ImageIO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.image.CourseGalleryImageService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import ru.mospolytech.dpo.amazon.AmazonClient;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.image.CourseGalleryImage;
import ru.mospolytech.dpo.domain.image.CourseMainImage;

@Controller("adminCourseGalleryImageController")
public class CourseGalleryImageController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "courses/gallery";
    
    private final CourseGalleryImageService courseGalleryImageService;
    private final CourseService courseService;
    private final AmazonClient amazonClient;

    public CourseGalleryImageController(CourseGalleryImageService courseGalleryImageService, CourseService courseService, AmazonClient amazonClient) {
        this.courseGalleryImageService = courseGalleryImageService;
        this.courseService = courseService;
        this.amazonClient = amazonClient;
    }
    
    @PostMapping("/admin/courses/{courseId}/gallery/new")
    public ModelAndView addCourseGalleryImage(
            @PathVariable Long courseId,
            @RequestParam("file") MultipartFile file
    )throws IOException {
        String url = amazonClient.uploadFile(file, imageControllerDir);
        ModelAndView mav = new ModelAndView("admin/fragments/course/courseGalleryImageItem :: courseGalleryImageItem");

        Course course = courseService.findById(courseId);

        CourseGalleryImage courseGalleryImage = new CourseGalleryImage();
        courseGalleryImage.setName(url);
        courseGalleryImage.setCourse(course);
        course.getCourseGalleryImages().add(courseGalleryImage);
        courseGalleryImageService.save(courseGalleryImage);

        mav.addObject("galleryItems", Arrays.asList(courseGalleryImage));
        
        return mav;
    }
    
    @DeleteMapping("/admin/courses/{courseId}/gallery/{id}")
    public @ResponseBody void deleteGalleryCourseImage(
            @PathVariable Long courseId,
            @PathVariable Long id
    ) throws IOException {
        CourseGalleryImage courseGalleryImage = courseGalleryImageService.findById(id);
        amazonClient.deleteFileFromS3Bucket(courseGalleryImage.getName(), imageControllerDir);
        
        courseGalleryImageService.delete(courseGalleryImage);
    }
}

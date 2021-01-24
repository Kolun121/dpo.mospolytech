package ru.mospolytech.dpo.controllers.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.Teacher;
import ru.mospolytech.dpo.domain.EducationalProgramStage;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.TeacherService;
import org.springframework.validation.BindingResult;
import ru.mospolytech.dpo.domain.EntityId;
import ru.mospolytech.dpo.service.EducationalProgramStageService;
import static ru.mospolytech.dpo.specification.CourseSpecification.hasVersion;


@Slf4j
@Controller("adminCoursesController")
@RequestMapping("/admin/courses")
public class CoursesController {
    @Value("${upload.path}")
    private String uploadPath;
    private final String imageControllerDir = "/courses/main_image";
    
    private final CourseService courseService;
    private final TeacherService teacherService;

    CoursesController(CourseService courseService, 
            TeacherService teacherService
    ) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
        dataBinder.setDisallowedFields("version");
    }
    
    @GetMapping
    public String getCoursesListPage(Model model,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, value = 15) Pageable pageable) {

        Page<Course> courses = courseService.findAllPageableSpec(hasVersion(0l), pageable);
        
        model.addAttribute("courses", courses);
        return "admin/courses/coursesList";
    }
    
    @GetMapping("{id}") 
    public String getCourseById(@PathVariable String id, Model model) {
        Course course = courseService.findById(Long.parseLong(id));
        System.out.println(model.getAttribute("educationalProgramStagesCurrentVersion"));
        model.addAttribute("course", course);
        model.addAttribute("teachers", teacherService.findAll());
        return "admin/courses/updateCourse";
    }
    
    @PostMapping("/new")
    public @ResponseBody String newCourse(Model model, Principal principal){
        Long maxId = courseService.maxId();
        Course newCourse = new Course();
        
        //Кем создан
        newCourse.setCreatedBy(principal.getName());
        newCourse.setId(++maxId);
        newCourse.setVersion(0l);
        Course savedCourse = courseService.save(newCourse);

        return savedCourse.getId().toString();
    }
    
    @PostMapping("{id}")
    public String updateCourseById(Model model, @PathVariable Long id, @Valid Course course, BindingResult result, Principal principal){
        Course currentCourse = courseService.findById(id).clone();        
        
        //Устанавливаем максимальную версию
        Long maxVersion = courseService.maxVersionWhereId(id) + 1;
        
        currentCourse.setId(id);
        //Выставляем следующую свободную версию
        currentCourse.setVersion(maxVersion);
        
        //Выставляем уникальный url
        currentCourse.setUrlSegment(id + "" + maxVersion);
        
        course.setId(id);
        //Выставляем текущую рабочую версию (версия 0 означает, что данный курс является новешим)
        course.setVersion(0l);
                
        //Кем отредактирован
        course.setEdditedBy(principal.getName());
        
        course.setMainImage(currentCourse.getMainImage());

        //Только в случае, когда были вненсены какие либо изменения
        if(!course.equals(currentCourse)){
            //Сохраняем текущую версию курса
            courseService.save(currentCourse);
            //Сохраняем новую версию курса
            courseService.save(course);
        }
        
        System.out.println(result.getAllErrors());
        
        return "redirect:/admin/courses/" + id;
    }
    
    @GetMapping("{id}/versions") 
    public String getCourseVersions(@PathVariable String id, Model model) {
        List<Course> courses = courseService.findAllByIdByOrderByCreatedAtAsc(Long.parseLong(id));
        model.addAttribute("courseVersions", courses);
        return "admin/courses/courseVersions";
    }
    
    @GetMapping("{id}/versions/{versionId}") 
    public String getCourseVersions(@PathVariable String id, @PathVariable String versionId, Model model) {
        List<Course> courses = courseService.findAllByIdByOrderByCreatedAtAsc(Long.parseLong(id));
        model.addAttribute("courseVersions", courses);
        model.addAttribute("chosenCourse", courseService.findByIdAndVersion(Long.parseLong(id), Long.parseLong(versionId)));
        return "admin/courses/courseVersions";
    }
    
    @PostMapping("{id}/versions/{versionId}") 
    public String changeCourseVersions(@PathVariable String id, @PathVariable String versionId, Model model) {
        Course currentCourse = courseService.findById(Long.parseLong(id)).clone();    
        Course desiredCourseVersion = courseService.findByIdAndVersion(Long.parseLong(id), Long.parseLong(versionId)).clone();
        Long maxVersion = courseService.maxVersionWhereId(Long.parseLong(id)) + 1;
        
        currentCourse.setId(Long.parseLong(id));
        //Выставляем следующую свободную версию
        currentCourse.setVersion(maxVersion);
        
        desiredCourseVersion.setUrlSegment(currentCourse.getUrlSegment());
        //Выставляем уникальный url
        currentCourse.setUrlSegment(id + "" + maxVersion);
        
        desiredCourseVersion.setId(Long.parseLong(id));
        //Выставляем текущую рабочую версию (версия 0 означает, что данный курс является новешим)
        desiredCourseVersion.setVersion(0l);
        
        courseService.save(currentCourse);
        courseService.save(desiredCourseVersion);
        return "redirect:/admin/courses/" + id + "/versions/" + 0;
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteCourseById(@PathVariable String id){
        List<Course> courses = courseService.findAllByIdByOrderByCreatedAtAsc(Long.parseLong(id));
        courses.forEach((c) -> {
            //удаляем картинки
            if(c.getMainImage() != null){
                Path imageToDeletePath = Paths.get(uploadPath + imageControllerDir + "/" + c.getMainImage().getName());
                try {
                    Files.delete(imageToDeletePath);
                } catch (IOException ex) {
                    Logger.getLogger(CoursesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            courseService.delete(c);
        });
    }

}

package ru.mospolytech.dpo.controllers.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.service.CourseService;

@Slf4j
@Controller("adminCoursesController")
@RequestMapping("/admin/courses")
public class CoursesController {
    private final CourseService courseService;

    CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "admin/courses/index";
    }
    
    @GetMapping("/update/{id}")
    public String updateCourseById(@PathVariable String id, Model model) {
        model.addAttribute("course", courseService.findById(Long.parseLong(id)));
        return "admin/courses/createOrUpdateCourse";
    }
    
    @GetMapping("/new")
    public String newRecipe(Model model){
        model.addAttribute("course", new Course());

        return "admin/courses/createOrUpdateCourse";
    }
    
    @PostMapping("/update/{id}")
    public String createOrUpdate(@PathVariable Long id, @ModelAttribute Course course){
        course.setId(id);
        Course savedCourse = courseService.save(course);

        return "redirect:/admin/courses/update/" + savedCourse.getId();
    }
    
    @GetMapping("/delete/{id}")
    public String deleteCourseById(@PathVariable String id){
        log.debug("Deleting id: " + id);
        courseService.deleteById(Long.valueOf(id));
        return "redirect:/admin/courses";
    }

}

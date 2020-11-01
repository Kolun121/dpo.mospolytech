package ru.mospolytech.dpo.controllers.admin;

import lombok.extern.slf4j.Slf4j;
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
    public String getCoursesListPage(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "admin/courses/coursesList";
    }
    
    @GetMapping("{id}")
    public String getCourseById(@PathVariable String id, Model model) {
        model.addAttribute("course", courseService.findById(Long.parseLong(id)));
        return "admin/courses/updateCourse";
    }
    
    @PostMapping("/new")
    public @ResponseBody String newCourse(Model model){
        Course savedCourse = courseService.save(new Course());

        return savedCourse.getId().toString();
    }
    
    @PostMapping("{id}")
    public String updateCourseById(@PathVariable Long id, @ModelAttribute Course course){
        course.setId(id);
        Course savedCourse = courseService.save(course);

        return "redirect:/admin/courses/" + savedCourse.getId();
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteCourseById(@PathVariable String id){
        courseService.deleteById(Long.valueOf(id));
    }

}

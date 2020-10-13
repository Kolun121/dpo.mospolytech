package ru.mospolytech.dpo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.service.CourseService;

@Controller("adminCoursesController")
@RequestMapping("/admin/courses")
public class CoursesController {
    private final CourseService courseService;

    CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("courses", courseService.findAll());
        return "admin/courses/index";
    }
}

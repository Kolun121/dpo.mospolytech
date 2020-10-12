package ru.mospolytech.dpo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("adminCoursesController")
@RequestMapping("/admin/courses")
public class CoursesController {
    
    @GetMapping
    public String getIndexPage(Model model) {

        return "admin/index";
    }
}

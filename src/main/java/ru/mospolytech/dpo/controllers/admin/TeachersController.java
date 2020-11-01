package ru.mospolytech.dpo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mospolytech.dpo.service.TeacherService;

@Controller("adminTeachersController")
@RequestMapping("/admin/teachers")
public class TeachersController {
    private final TeacherService teacherService;

    TeachersController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "admin/teachers/teachersList";
    }
}

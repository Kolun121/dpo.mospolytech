package ru.mospolytech.dpo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mospolytech.dpo.domain.Teacher;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.TeacherService;

@Controller("adminTeachersController")
@RequestMapping("/admin/teachers")
public class TeachersController {
    private final TeacherService teacherService;
    private final CourseService courseService;

    TeachersController(TeacherService teacherService, CourseService courseService) {
        this.teacherService = teacherService;
        this.courseService = courseService;
    }
    
    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }
    
    @GetMapping
    public String getTeachersListPage(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "admin/teachers/teachersList";
    }
    
    @GetMapping("{id}")
    public String getTeacherById(@PathVariable Long id, Model model) {
        model.addAttribute("teacher", teacherService.findById(id));
        model.addAttribute("courses", courseService.findAll());
        return "admin/teachers/updateTeacher";
    }
    
    @PostMapping("/new")
    public @ResponseBody String newTeacher() {
        Teacher savedTeacher = teacherService.save(new Teacher());
        return savedTeacher.getId().toString();
    }
    
    @PostMapping("{id}")
    public String updateTeacherById(@PathVariable Long id, @ModelAttribute Teacher teacher){
        teacher.setId(id);
        Teacher savedTeacher = teacherService.save(teacher);

        return "redirect:/admin/teachers/" + savedTeacher.getId();
    }
    
    @DeleteMapping("{id}")
    public @ResponseBody void deleteTeacherById(@PathVariable Long id){
        teacherService.deleteById(id);
    }
}

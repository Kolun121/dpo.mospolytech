package ru.mospolytech.dpo.controllers.admin;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.mospolytech.dpo.domain.EducationalProgramStage;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.EducationalProgramStageService;

@Slf4j
@Controller("adminEducationalProgramStagesController")
//@RequestMapping("/admin/courses")
public class EducationalProgramStagesController {
    private final EducationalProgramStageService educationalProgramStageService;
    private final CourseService courseService;

    EducationalProgramStagesController(EducationalProgramStageService educationalProgramStageService, CourseService courseService){
        this.educationalProgramStageService = educationalProgramStageService;
        this.courseService = courseService;
    }
    
    @PostMapping("/admin/courses/{courseId}/stages/new")
    public ModelAndView createEducationalStageItem(@PathVariable Long courseId) {
        ModelAndView mav = new ModelAndView("admin/fragments/course/courseStageItem :: courseStageItem");
        EducationalProgramStage educationalProgramStage = new EducationalProgramStage();
        educationalProgramStage.setCourse(courseService.findById(courseId));
        mav.addObject("stageItems", Arrays.asList(educationalProgramStageService.save(educationalProgramStage)));
        return mav;
    }

    @PostMapping("/admin/courses/{courseId}/stages/{id}")
    public @ResponseBody void updateStageById(
            @RequestParam(value = "title", required = false) String title, 
            @RequestParam(value = "description", required = false) String description, 
            @PathVariable Long courseId, 
            @PathVariable Long id)
    {
        EducationalProgramStage educationalProgramStage = new EducationalProgramStage();
        
        educationalProgramStage.setId(id);
        educationalProgramStage.setDescription(description);
        educationalProgramStage.setTitle(title);
        
        educationalProgramStage.setCourse(courseService.findById(courseId));
        educationalProgramStageService.save(educationalProgramStage);
    }
   
    @DeleteMapping("/admin/courses/{courseId}/stages/{id}")
    public @ResponseBody void deleteStageById(@PathVariable Long id){
        educationalProgramStageService.deleteById(id);
    }

}
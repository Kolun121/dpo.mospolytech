package ru.mospolytech.dpo.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.enumeration.CourseCompetency;
import ru.mospolytech.dpo.domain.enumeration.CourseField;
import ru.mospolytech.dpo.domain.enumeration.CourseForm;
import ru.mospolytech.dpo.domain.enumeration.CourseStudyLocation;
import ru.mospolytech.dpo.domain.enumeration.CourseTargetEntity;
import ru.mospolytech.dpo.domain.enumeration.CourseType;
import ru.mospolytech.dpo.domain.enumeration.Status;
import ru.mospolytech.dpo.service.CourseService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.PathVariable;
import static ru.mospolytech.dpo.specification.CourseSpecification.*;

@Controller
@RequestMapping("/courses")
public class CoursesController {
    private final CourseService courseService;

    CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public String getCoursesList(
            Model model,
            @RequestParam(value = "course_target_entity", required = false) String courseTargetEntity, 
            @RequestParam(value = "course_field", required = false) String courseField, 
            @RequestParam(value = "course_type", required = false) String courseType, 
            @RequestParam(value = "course_competency", required = false) String courseCompetency, 
            @RequestParam(value = "course_study_location", required = false) String courseStudyLocation, 
            @RequestParam(value = "course_form", required = false) String courseForm,
            @RequestParam(value = "price", required = false) String price,
            @PageableDefault(sort = { "id" }, direction = Sort.Direction.DESC, value = 9) Pageable pageable
    ) {
        //Значения минимальной и максимальной возможной цены для курсов
        Integer minPrice = courseService.min();
        Integer maxPrice = courseService.max();
        
        Map<String, List<? extends Enum>> appliedFilters = new HashMap();
        
        List<CourseTargetEntity> courseTargetEntityList = new ArrayList<>();
        String[] courseTargetEntityArr = (courseTargetEntity == null) ? new String[]{} : courseTargetEntity.split("-");
        for(String s: courseTargetEntityArr){
            try {
                courseTargetEntityList.add(CourseTargetEntity.valueOf(s));
            } catch (IllegalArgumentException e) {
            }
        }
        
        appliedFilters.put("course_target_entity", courseTargetEntityList);
        
        List<CourseField> courseFieldList = new ArrayList<>();
        String[] courseFieldArr = (courseField == null) ? new String[]{} : courseField.split("-");
        for(String s: courseFieldArr){
            try {
                courseFieldList.add(CourseField.valueOf(s));
            } catch (IllegalArgumentException e) {
            }
        }
        
        appliedFilters.put("course_field", courseFieldList);

        List<CourseType> courseTypeList = new ArrayList<>();
        String[] courseTypeArr = (courseType == null) ? new String[]{} : courseType.split("-");
        for(String s: courseTypeArr){
            try {
                courseTypeList.add(CourseType.valueOf(s));
            } catch (IllegalArgumentException e) {
            }
        }
        
        appliedFilters.put("course_type", courseTypeList);
        
        List<CourseCompetency> courseCompetencyList = new ArrayList<>();
        String[] courseCompetencyArr = (courseCompetency == null) ? new String[]{} : courseCompetency.split("-");
        for(String s: courseCompetencyArr){
            try {
                courseCompetencyList.add(CourseCompetency.valueOf(s));
            } catch (IllegalArgumentException e) {
            }
        }
        
        appliedFilters.put("course_competency", courseCompetencyList);
        
        List<CourseStudyLocation> courseStudyLocationList = new ArrayList<>();
        String[] courseStudyLocationArr = (courseStudyLocation == null) ? new String[]{} : courseStudyLocation.split("-");
        for(String s: courseStudyLocationArr){
            courseStudyLocationList.add(CourseStudyLocation.valueOf(s));
            try {
                courseStudyLocationList.add(CourseStudyLocation.valueOf(s));
            } catch (IllegalArgumentException e) {
            }
        }
        
        appliedFilters.put("course_study_location", courseStudyLocationList);
        
        List<CourseForm> courseFormList = new ArrayList<>();
        String[] courseFormArr = (courseForm == null) ? new String[]{} : courseForm.split("-");
        for(String s: courseFormArr){
            try {
                courseFormList.add(CourseForm.valueOf(s));
            } catch (IllegalArgumentException e) {
            }
        }
        
        appliedFilters.put("course_form", courseFormList);

        //цена
        List<Integer> coursePriceList = new ArrayList<>();
        String[] priceArr = (price == null) ? new String[]{} : price.split("-");
        for(String s: priceArr){
            try {
                coursePriceList.add(Integer.parseInt(s));
            } catch (IllegalArgumentException e) {
            }
        }
        

        Page<Course> courses = courseService.findAllPageableSpec(Specification
                        .where(hasPriceBetween(
                                coursePriceList.isEmpty() ? minPrice : coursePriceList.get(0), 
                                coursePriceList.isEmpty() ? maxPrice : coursePriceList.get(1)
                        ))
                        .and(hasCourseField(courseFieldList))
                        .and(hasCourseCompetency(courseCompetencyList))
                        .and(hasCourseForm(courseFormList))
                        .and(hasCourseStudyLocation(courseStudyLocationList))
                        .and(hasCourseTargetEntity(courseTargetEntityList))
                        .and(hasCourseCompetency(courseCompetencyList))
                        .and(hasPublishedStatus()),
                        pageable);
        

        model.addAttribute("appliedFilters", appliedFilters);
        
        model.addAttribute("courseTargetEntity", Arrays.asList(CourseTargetEntity.values()));
        model.addAttribute("courseField", Arrays.asList(CourseField.values()));
        model.addAttribute("courseType", Arrays.asList(CourseType.values()));
        model.addAttribute("courseCompetency", Arrays.asList(CourseCompetency.values()));
        model.addAttribute("courseStudyLocation", Arrays.asList(CourseStudyLocation.values()));
        model.addAttribute("courseForm", Arrays.asList(CourseForm.values()));
        
        model.addAttribute("userCoursePrice", coursePriceList);
       
        model.addAttribute("maxCoursePrice", maxPrice);
        model.addAttribute("minCoursePrice", minPrice);
        
        model.addAttribute("courses", courses);

        return "courses/coursesList";
    }
    
    @PostMapping("/ajaxCoursesFilter")
    public String ajaxCoursesFilter(Model model, 
            @RequestBody Map<String, String[]> filters) {
        
        System.out.println("----------------");
        for(String s: filters.get("course_form")){
            System.out.println(s);
        }
        
        for(String s: filters.get("course_form")){
            System.out.println(s);
        }
        return "courses/coursesList";
    }
    
    @GetMapping("{urlSegment}")
    public String getCourseByUrlSegment(@PathVariable String urlSegment, Model model) {
        Course course = courseService.findByUrlSegment(urlSegment);
        model.addAttribute("course", course);
        
        return "courses/showCourse";
    }
}

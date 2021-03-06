package ru.mospolytech.dpo.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.UserFeedBack;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.UserFeedBackService;
import static ru.mospolytech.dpo.specification.CourseSpecification.hasDateBetweenCurrentDateAndYear;
import static ru.mospolytech.dpo.specification.CourseSpecification.hasPublishedStatus;
import static ru.mospolytech.dpo.specification.CourseSpecification.hasVersion;

@Controller
@RequestMapping("/calendar")
public class CalendarController {

    private final CourseService courseService;

    CalendarController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping()
    public String getCalendarList(
            Model model,
            @PageableDefault(sort = { "courseStartDate" }, direction = Sort.Direction.ASC) Pageable limit
    ) {
        Page<Course> courses = courseService.findAllPageableSpec(Specification
                        .where(hasDateBetweenCurrentDateAndYear())
                        .and(hasPublishedStatus())
                        .and(hasVersion(0l)),
                        limit);
        model.addAttribute("courses", courses);
        return "calendar/calendarList";
    }
}

package ru.mospolytech.dpo.controllers;

import java.util.Arrays;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.mospolytech.dpo.domain.Review;
import ru.mospolytech.dpo.service.CourseService;
import ru.mospolytech.dpo.service.ReviewService;

@Controller
public class ReviewController {   
    private final ReviewService reviewService;
    private final CourseService courseService;
    
    public ReviewController(ReviewService reviewService, CourseService courseService){
        this.reviewService = reviewService;
        this.courseService = courseService;
    }
    
    @PostMapping("/courses/{courseUrl}/review/new")
    public @ResponseBody ModelAndView createReview(@PathVariable String courseUrl, 
            @RequestParam(value = "name", required = false) String name, 
            @RequestParam(value = "text", required = false) String text) {

        ModelAndView mav = new ModelAndView("fragments/course/courseReviews :: courseReviews");
        Review review = new Review();
        review.setName(name);
        review.setText(text);
        review.setCourse(courseService.findByUrlSegment(courseUrl));
        mav.addObject("courseReviews", Arrays.asList(reviewService.save(review)));
        System.out.println(mav);
        return mav;
    }
   
//    @DeleteMapping("/courses/{courseId}/review/{id}")
//    public @ResponseBody void deleteStageById(@PathVariable Long id){
//        educationalProgramStageService.deleteById(id);
//    }
}

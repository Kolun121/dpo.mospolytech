package ru.mospolytech.dpo.specification;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import ru.mospolytech.dpo.domain.Course;
import ru.mospolytech.dpo.domain.Course_;
import ru.mospolytech.dpo.domain.enumeration.CourseCompetency;
import ru.mospolytech.dpo.domain.enumeration.CourseField;
import ru.mospolytech.dpo.domain.enumeration.CourseForm;
import ru.mospolytech.dpo.domain.enumeration.CourseStudyLocation;
import ru.mospolytech.dpo.domain.enumeration.CourseTargetEntity;
import ru.mospolytech.dpo.domain.enumeration.CourseType;
import ru.mospolytech.dpo.domain.enumeration.Status;

public class CourseSpecification {
 
    public static Specification<Course> hasPriceBetween(Integer min, Integer max) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<Integer> coursePrice = root.get(Course_.coursePrice);
            return builder.and(
                builder.greaterThanOrEqualTo(
                   coursePrice, min),
                builder.lessThanOrEqualTo(
                   coursePrice, max)
            );
        };
    }
    
    public static Specification<Course> hasCourseField(List<CourseField> enumList) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<CourseField> courseEnumField = root.get(Course_.courseField);
            In in = builder.in(courseEnumField);
            
            if (enumList == null || enumList.isEmpty()) {
                for(CourseField enumValue : CourseField.values()){
                    in.value(enumValue);
                }
            }
            else {
                for(CourseField enumValue : enumList){
                    in.value(enumValue);
                }
            }
            return in;
        };
    }

    public static Specification<Course> hasCourseCompetency(List<CourseCompetency> enumList) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<CourseCompetency> courseEnumField = root.get(Course_.courseCompetency);
            In in = builder.in(courseEnumField);
            
            if (enumList == null || enumList.isEmpty()) {
                for(CourseCompetency enumValue : CourseCompetency.values()){
                    in.value(enumValue);
                }
            }
            else {
                for(CourseCompetency enumValue : enumList){
                    in.value(enumValue);
                }
            }
            
            return in;
        };
    }
    
    public static Specification<Course> hasCourseForm(List<CourseForm> enumList) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<CourseForm> courseEnumField = root.get(Course_.courseForm);
            In in = builder.in(courseEnumField);
            
            if (enumList == null || enumList.isEmpty()) {
                for(CourseForm enumValue : CourseForm.values()){
                    in.value(enumValue);
                }
            }
            else {
                for(CourseForm enumValue : enumList){
                    in.value(enumValue);
                }
            }
            
            return in;
        };
    }
    
    public static Specification<Course> hasCourseStudyLocation(List<CourseStudyLocation> enumList) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<CourseStudyLocation> courseEnumField = root.get(Course_.courseStudyLocation);
            In in = builder.in(courseEnumField);
            
            if (enumList == null || enumList.isEmpty()) {
                for(CourseStudyLocation enumValue : CourseStudyLocation.values()){
                    in.value(enumValue);
                }
            }
            else {
                for(CourseStudyLocation enumValue : enumList){
                    in.value(enumValue);
                }
            }
            
            return in;
        };
    }

    public static Specification<Course> hasCourseTargetEntity(List<CourseTargetEntity> enumList) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<CourseTargetEntity> courseEnumField = root.get(Course_.courseTargetEntity);
            In in = builder.in(courseEnumField);
            
            if (enumList == null || enumList.isEmpty()) {
                for(CourseTargetEntity enumValue : CourseTargetEntity.values()){
                    in.value(enumValue);
                }
            }
            else {
                for(CourseTargetEntity enumValue : enumList){
                    in.value(enumValue);
                }
            }
            
            return in;
        };
    }
    
    public static Specification<Course> hasCourseType(List<CourseType> enumList) {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<CourseType> courseEnumField = root.get(Course_.courseType);
            In in = builder.in(courseEnumField);
            
            if (enumList == null || enumList.isEmpty()) {
                for(CourseType enumValue : CourseType.values()){
                    in.value(enumValue);
                }
            }
            else {
                for(CourseType enumValue : enumList){
                    in.value(enumValue);
                }
            }
            
            return in;
        };
    }
    
    public static Specification<Course> hasPublishedStatus() {
        return (Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Path<Status> courseStatus = root.get(Course_.courseStatus);
            Predicate equalPredicate = builder.equal(courseStatus, Status.PUBLISHED);
            return equalPredicate;   
        };
    }

}

package ru.mospolytech.dpo.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Enumerated(value = EnumType.STRING)
    private String title;
    private String courseGoal;
//    private String courseAudience String
//    private String courseSubject String
//    private String coursePrice Integer
//    private String courseTime Integer
//    private String courseDocument String
//    private String urlSegment String
//    private String courseStatus CourseStatus
//    private String date Date
//    private String courseField CourseField
//    private String courseType CourseType
//    private String courseCompetency CourseCompetency
//    private String courseForm CourseForm
//    private StringcourseStudyLocation CourseStudyLocation
//    private String courseTargetEntity CourseTargetEntity
//    private String createdAt Date
}

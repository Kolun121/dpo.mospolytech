package ru.mospolytech.dpo.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Enumerated(value = EnumType.STRING)
    private String title;
    private String courseGoal;
    private String courseAudience;
    private String courseSubject;
    private Integer coursePrice;
    private Integer courseTime;
    private String courseDocument;
    private String urlSegment;
//    private CourseStatus courseStatus;

//    private CourseField courseField;
//    private CourseType courseType;
//    private CourseCompetency courseCompetency;
//    private CourseForm courseForm;
//    private CourseStudyLocation courseStudyLocation;
//    private CourseTargetEntity courseTargetEntity;
    
    private Date createdAt;
    
    @PrePersist
    void placedAt() {
        this.createdAt = new Date();
    }
}

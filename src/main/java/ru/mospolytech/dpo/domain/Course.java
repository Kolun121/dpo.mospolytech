package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.mospolytech.dpo.domain.enumeration.CourseCompetency;
import ru.mospolytech.dpo.domain.enumeration.CourseField;
import ru.mospolytech.dpo.domain.enumeration.CourseForm;
import ru.mospolytech.dpo.domain.enumeration.CourseStatus;
import ru.mospolytech.dpo.domain.enumeration.CourseStudyLocation;
import ru.mospolytech.dpo.domain.enumeration.CourseTargetEntity;
import ru.mospolytech.dpo.domain.enumeration.CourseType;
import ru.mospolytech.dpo.domain.image.CourseMainImage;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String courseGoal;
    private String courseAudience;
    private String courseSubject;
    private Integer coursePrice;
    private Integer courseTime;
    private String courseDocument;
    @Column(unique = true)
    private String urlSegment;

    @Enumerated(value = EnumType.STRING)
    private CourseField courseField;
    
    @Enumerated(value = EnumType.STRING)
    private CourseType courseType;
    
    @Enumerated(value = EnumType.STRING)
    private CourseCompetency courseCompetency;
    
    @Enumerated(value = EnumType.STRING)
    private CourseForm courseForm;
    
    @Enumerated(value = EnumType.STRING)
    private CourseStudyLocation courseStudyLocation;
    
    @Enumerated(value = EnumType.STRING)
    private CourseTargetEntity courseTargetEntity;
    
    @Enumerated(value = EnumType.STRING)
    private CourseStatus courseStatus = CourseStatus.UNPUBLISHED;
    
    @OneToOne(cascade = CascadeType.ALL)
    private CourseMainImage mainImage;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<EducationalProgramStage> educationalProgramStages = new ArrayList<>();
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
    
//    @PrePersist
//    void placedAt() {
//        this.createdAt = new Timestamp(new Date().getTime());
////        this.createdAt = new Date();
//    }
}

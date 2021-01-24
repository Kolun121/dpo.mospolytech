package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import javax.validation.constraints.NotBlank;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.mospolytech.dpo.domain.enumeration.CourseCompetency;
import ru.mospolytech.dpo.domain.enumeration.CourseField;
import ru.mospolytech.dpo.domain.enumeration.CourseForm;
import ru.mospolytech.dpo.domain.enumeration.Status;
import ru.mospolytech.dpo.domain.enumeration.CourseStudyLocation;
import ru.mospolytech.dpo.domain.enumeration.CourseTargetEntity;
import ru.mospolytech.dpo.domain.enumeration.CourseType;
import ru.mospolytech.dpo.domain.image.CourseGalleryImage;
import ru.mospolytech.dpo.domain.image.CourseMainImage;

@Getter
@Setter
@Entity
@Table(name = "courses")
@IdClass(EntityId.class)
public class Course implements Serializable{
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long version;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String title;
    private String courseGoal;
    private String courseAudience;
    private String courseSubject;
    private Integer coursePrice;
    private Integer courseTime;
    private String courseDocument;
    
    private Date courseStartDate;
    
    @ManyToMany
    @JoinTable(name = "course_teacher",
        joinColumns = {
                        @JoinColumn(name = "course_id", referencedColumnName = "id"),
                        @JoinColumn(name = "Version", referencedColumnName = "Version")
                    },
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers = new HashSet<>();
    
    @Column(unique = true)
    private String urlSegment;
    
//    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private CourseField courseField;
    
//    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private CourseType courseType;
    
//    @NotBlank    
    @Enumerated(value = EnumType.STRING)
    private CourseCompetency courseCompetency;
    
//    @NotBlank 
    @Enumerated(value = EnumType.STRING)
    private CourseForm courseForm;
    
//    @NotBlank 
    @Enumerated(value = EnumType.STRING)
    private CourseStudyLocation courseStudyLocation;
    
//    @NotBlank 
    @Enumerated(value = EnumType.STRING)
    private CourseTargetEntity courseTargetEntity;
    
    @Enumerated(value = EnumType.STRING)
    private Status courseStatus = Status.UNPUBLISHED;
    
    @OneToOne(cascade = CascadeType.ALL)
    private CourseMainImage mainImage;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<EducationalProgramStage> educationalProgramStages = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<CourseGalleryImage> courseGalleryImages = new ArrayList<>();
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
    
    private String createdBy;
    private String edditedBy;

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Course)) {
            return false;
        }

        
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(title, course.title) &&
                Objects.equals(courseGoal, course.courseGoal) &&
                Objects.equals(courseAudience, course.courseAudience) &&
                Objects.equals(courseSubject, course.courseSubject) &&
                Objects.equals(coursePrice, course.coursePrice) &&
                Objects.equals(courseTime, course.courseTime) &&
                Objects.equals(courseDocument, course.courseDocument) &&
                Objects.equals(courseDocument, course.courseDocument) &&
                Objects.equals(courseStartDate, course.courseStartDate) &&
                Objects.equals(teachers, course.teachers) &&
                Objects.equals(courseField, course.courseField) &&
                Objects.equals(courseType, course.courseType) &&
                Objects.equals(courseCompetency, course.courseCompetency) &&
                Objects.equals(courseForm, course.courseForm) &&
                Objects.equals(courseStudyLocation, course.courseStudyLocation) &&
                Objects.equals(courseTargetEntity, course.courseTargetEntity) &&
                Objects.equals(courseStatus, course.courseStatus)
//                Objects.equals(mainImage, course.mainImage) &&
//                Objects.equals(educationalProgramStages, course.educationalProgramStages) 
//                Objects.equals(mainImage, course.mainImage) &&
//                Objects.equals(mainImage, course.mainImage) &&
//                Objects.equals(mainImage, course.mainImage)
                ;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(
                title, 
                id,
                courseGoal,
                courseAudience,
                courseSubject,
                coursePrice,
                courseTime,
                courseDocument,
                courseStartDate,
                courseField,
                courseType,
                courseCompetency,
                courseForm,
                courseStudyLocation,
                courseTargetEntity,
                courseStatus
                        );
    }
    
    @Override
    public Course clone() {
        Course course = new Course();
        course.setTitle(this.title);
        course.setCourseGoal(this.courseGoal);
        course.setCourseAudience(this.courseAudience);
        course.setCourseSubject(this.courseSubject);
        course.setCoursePrice(this.coursePrice);
        course.setCourseTime(this.courseTime);
        course.setCourseDocument(this.courseDocument);
        course.setCourseStartDate(this.courseStartDate); 
        course.setTeachers(this.teachers);
        course.setUrlSegment(this.urlSegment);
        course.setCourseField(this.courseField);
        course.setCourseType(this.courseType);
        course.setCourseCompetency(this.courseCompetency);
        course.setCourseForm(this.courseForm);
        course.setCourseStudyLocation(this.courseStudyLocation);
        course.setCourseTargetEntity(this.courseTargetEntity);
        course.setCourseStatus(this.courseStatus);
        course.setMainImage(this.mainImage);
        course.setEducationalProgramStages(this.educationalProgramStages);
        course.setCourseGalleryImages(this.courseGalleryImages);
        course.setCreatedAt(this.createdAt);
        course.setUpdatedAt(this.updatedAt);
        course.setCreatedBy(this.createdBy);
        course.setEdditedBy(this.edditedBy);
        
        return course;
                
    }
}

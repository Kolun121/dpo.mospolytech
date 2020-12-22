package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    
    @ManyToMany
    @JoinTable(name = "course_teacher",
        joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    private Set<Teacher> teachers = new HashSet<>();
    
    @Column(unique = true)
    private String urlSegment;
    
    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private CourseField courseField;
    
    @NotBlank
    @Enumerated(value = EnumType.STRING)
    private CourseType courseType;
    
    @NotBlank    
    @Enumerated(value = EnumType.STRING)
    private CourseCompetency courseCompetency;
    
    @NotBlank 
    @Enumerated(value = EnumType.STRING)
    private CourseForm courseForm;
    
    @NotBlank 
    @Enumerated(value = EnumType.STRING)
    private CourseStudyLocation courseStudyLocation;
    
    @NotBlank 
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

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return id == course.id &&
                Objects.equals(title, course.title);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}

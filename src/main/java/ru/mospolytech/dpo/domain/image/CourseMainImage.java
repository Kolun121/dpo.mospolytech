package ru.mospolytech.dpo.domain.image;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import ru.mospolytech.dpo.domain.Course;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CourseMainImage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @OneToOne
    private Course course;
}

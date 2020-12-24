package ru.mospolytech.dpo.domain.image;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import ru.mospolytech.dpo.domain.Course;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class CourseGalleryImage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    @ManyToOne
    private Course course;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof CourseGalleryImage)) {
            return false;
        }
        CourseGalleryImage courseGalleryImage = (CourseGalleryImage) o;
        return Objects.equals(id, courseGalleryImage.id) &&
                Objects.equals(name, courseGalleryImage.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

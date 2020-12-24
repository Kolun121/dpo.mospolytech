package ru.mospolytech.dpo.domain.image;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import ru.mospolytech.dpo.domain.Course;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
//    @JoinColumn(joinColumns = {
//                        @JoinColumn(name = "course_id", referencedColumnName = "id"),
//                        @JoinColumn(name = "Version", referencedColumnName = "Version")
//                    })
    private Course course;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof CourseMainImage)) {
            return false;
        }
        CourseMainImage courseMainImage = (CourseMainImage) o;
        return Objects.equals(id, courseMainImage.id) &&
                Objects.equals(name, courseMainImage.name);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

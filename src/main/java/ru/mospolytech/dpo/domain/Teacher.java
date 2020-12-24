package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.mospolytech.dpo.domain.image.TeacherMainImage;

@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String fullName;
    private String teacherOccupation;
    private String teacherInformation;
    private Date teacherDateOfBirth;
    
    @ManyToMany
    @JoinTable(name = "course_teacher",
        joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = {
                @JoinColumn(name = "course_id", referencedColumnName = "id"),
                @JoinColumn(name = "Version", referencedColumnName = "Version")
            })
    private Set<Course> courses = new HashSet();
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    private TeacherMainImage mainImage;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id) &&
                Objects.equals(fullName, teacher.fullName) &&
                Objects.equals(teacherOccupation, teacher.teacherOccupation) &&
                Objects.equals(teacherInformation, teacher.teacherInformation) &&
                Objects.equals(teacherDateOfBirth, teacher.teacherDateOfBirth) &&
                Objects.equals(mainImage, teacher.mainImage);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fullName, id);
    }
}

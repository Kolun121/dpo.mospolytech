package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EducationalProgramStage implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    
    @ManyToOne
    private Course course;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof EducationalProgramStage)) {
            return false;
        }
        EducationalProgramStage educationalProgramStage = (EducationalProgramStage) o;
        return Objects.equals(id, educationalProgramStage.id) &&
                Objects.equals(title, educationalProgramStage.title) &&
                Objects.equals(description, educationalProgramStage.description);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(description, id, title);
    }
}

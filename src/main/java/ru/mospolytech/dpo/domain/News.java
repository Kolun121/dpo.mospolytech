package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.mospolytech.dpo.domain.enumeration.Status;
import ru.mospolytech.dpo.domain.image.NewsMainImage;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String title;
    private String description;
    
    @Column(unique = true)
    private String urlSegment;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    private NewsMainImage newsMainImage;
    
    @Enumerated(value = EnumType.STRING)
    private Status newsStatus = Status.UNPUBLISHED;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof News)) {
            return false;
        }
        News news = (News) o;
        return id == news.id &&
                Objects.equals(title, news.title);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(title, id);
    }
}

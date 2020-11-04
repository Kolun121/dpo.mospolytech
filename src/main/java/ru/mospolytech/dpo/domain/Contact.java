package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.mospolytech.dpo.domain.image.ContactMainImage;

@Getter
@Setter
@Entity
@Table(name = "contacts")
public class Contact implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String fullName;
    private String occupation;
    private String phone;
    private String email;
    

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
    
    @OneToOne(cascade = CascadeType.ALL)
    private ContactMainImage contactMainImage;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof Contact)) {
            return false;
        }
        Contact contact = (Contact) o;
        return id == contact.id &&
                Objects.equals(fullName, contact.fullName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fullName, id);
    }
}

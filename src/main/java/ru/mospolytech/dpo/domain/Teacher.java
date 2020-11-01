package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt = new Timestamp(new Date().getTime());
    
    @UpdateTimestamp
    @Column
    private Timestamp updatedAt;
//    @OneToOne(cascade = CascadeType.ALL)
//    private ImageFile teacherMainImage;
}

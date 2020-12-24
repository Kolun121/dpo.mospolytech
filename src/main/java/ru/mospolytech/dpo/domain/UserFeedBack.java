/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.mospolytech.dpo.domain;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Getter
@Setter
@Entity
@Table(name = "user_feedback")
public class UserFeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    private String fullName;
    private String phone;
    private String email;
    private String question;
    
    private String courseTitle;
    

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof UserFeedBack)) {
            return false;
        }
        UserFeedBack userFeedBack = (UserFeedBack) o;
        return id == userFeedBack.id &&
                Objects.equals(fullName, userFeedBack.fullName);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fullName, id);
    }
}

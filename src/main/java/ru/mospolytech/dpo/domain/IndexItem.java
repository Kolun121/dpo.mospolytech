package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import ru.mospolytech.dpo.domain.image.IndexItemImage;

@Entity
@Getter
@Setter
public class IndexItem implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String text;
    private String url;
    
    @OneToOne
    private IndexItemImage indexImage;
    
    @Override
    public boolean equals(Object o) {

        if (o == this) return true;
        if (!(o instanceof IndexItem)) {
            return false;
        }
        IndexItem indexItem = (IndexItem) o;
        return Objects.equals(id, indexItem.id);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

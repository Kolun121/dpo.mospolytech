package ru.mospolytech.dpo.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Id;

public class EntityId implements Serializable {

    @Id
    private Long version;
    
    @Id
    private Long id;

    public EntityId() {
    }
    
    public EntityId(Long id, Long version) {
        this.version = version;
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long Version) {
        this.version = Version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityId entityId = (EntityId) o;
        return id.equals(entityId.id) &&
                version.equals(entityId.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }
}
package ru.mospolytech.dpo.domain.enumeration;

public enum CourseTargetEntity {
    INDIVIDUAL("Для физических лиц"), 
    PLURAL("Для юридических лиц");
    
    private final String value;
    
    CourseTargetEntity(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

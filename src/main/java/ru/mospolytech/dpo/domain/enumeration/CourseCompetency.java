package ru.mospolytech.dpo.domain.enumeration;

public enum CourseCompetency {
    PROFESSIONAL_COMPETENCE("Профессиональные компетенции"), 
    PERSONAL_COMPETENCE("Личностные компетенции");
    
    private final String value;
    
    CourseCompetency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

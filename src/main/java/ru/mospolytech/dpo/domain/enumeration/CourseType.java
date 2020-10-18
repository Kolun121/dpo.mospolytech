package ru.mospolytech.dpo.domain.enumeration;

public enum CourseType {
    TRAINING("Повышение квалификации"), 
    PROFESSIONAL_RETRAINING("Профессиональная переподготовка"), 
    ADDITIONAL_EDUCATIONAL_PROGRAMS("Дополнительные образовательные программы");
    
    private final String value;
    
    CourseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

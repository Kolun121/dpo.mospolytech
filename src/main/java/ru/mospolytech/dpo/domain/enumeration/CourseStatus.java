package ru.mospolytech.dpo.domain.enumeration;


public enum CourseStatus {
    UNPUBLISHED("Отложен"),
    PUBLISHED("Опубликован");
    
    private final String value;
    
    CourseStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
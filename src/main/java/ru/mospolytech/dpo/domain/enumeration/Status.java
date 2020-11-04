package ru.mospolytech.dpo.domain.enumeration;


public enum Status {
    UNPUBLISHED("Отложен"),
    PUBLISHED("Опубликован");
    
    private final String value;
    
    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
package ru.mospolytech.dpo.domain.enumeration;

public enum CourseForm {
    OCHNAYA("Очная"), 
    ZAOCHNAYA("Заочная"), 
    OCHNO_ZAOCHNAYA("Очно-заочная"), 
    REMOTE("Дистанционная");
    
    private final String value;
    
    CourseForm(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
